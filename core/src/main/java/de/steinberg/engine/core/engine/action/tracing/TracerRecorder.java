package de.steinberg.engine.core.engine.action.tracing;

import de.steinberg.engine.core.annotations.DisplayName;
import de.steinberg.engine.core.annotations.TooltipText;
import de.steinberg.engine.core.engine.action.AbstractAction;
import de.steinberg.engine.core.engine.control.Control;
import de.steinberg.engine.core.engine.selection.SelectionList;
import de.steinberg.engine.core.engine.setting.SettingsKey;
import de.steinberg.engine.core.engine.status.Status;
import de.steinberg.engine.core.exception.script.ErrorStreamParserException;
import de.steinberg.engine.core.exception.script.ScriptException;
import de.steinberg.engine.core.process.ScriptRunner;
import de.steinberg.engine.core.process.Scripts;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by LKLeen on 29.12.2016.
 */
@Slf4j
@DisplayName("Trace Recorder")
public class TracerRecorder extends AbstractAction {

    private enum State {STOPPED, RUNNING, WRITING};

    @TooltipText("start the tracer in the background. the tracer must be running before traces can be captured with 'capture trace'")
    private class Start implements Control {
        @Override
        public void trigger() {
            startTrace();
        }
    }

    @TooltipText("stops running the tracer in the background.")
    private class Stop implements Control {
        @Override
        public void trigger() {
            stopTrace();
        }
    }

    @TooltipText("captures a trace for the last ~10 seconds. the tracer must be started before capturing")
    private class Capture implements Control {
        @Override
        public void trigger() {
            flushTrace();
        }
    }

    @TooltipText("tracing buffer size eg. 128 256 1024")
    private class BufferSettingsKey implements SettingsKey {
        @Override
        public String get() {
            return "buffer size";
        }
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd.HHmmss");

    @Inject
    ScriptRunner scriptRunner;

    private State state = State.STOPPED;

    private final String TRACER_TYPE = "type";
    private final String TRACER_TYPE_FULL = "full";
    private final String TRACER_TYPE_THREAD_PRIORITIES = "thread priorities";

    private final Status RUNNING = new Status(Status.Color.GREEN, "running");
    private final Status STOPPED = new Status(Status.Color.RED, "stopped");
    private final Status WRITING = new Status(Status.Color.YELLOW, "writing trace");

    private final SettingsKey bufferSize = new BufferSettingsKey();

    private final Control start = new Start();
    private final Control stop = new Stop();
    private final Control captureTrace = new Capture();

    public TracerRecorder() {
        SelectionList tracerTypes = new SelectionList();
        tracerTypes.add(TRACER_TYPE_FULL);
        tracerTypes.add(TRACER_TYPE_THREAD_PRIORITIES);
        selections.put(TRACER_TYPE, tracerTypes);
        settings.put(bufferSize, "1024");
        controls.put("start", start);
        controls.put("stop", stop);
        controls.put("capture trace", captureTrace);
        statusProperty = new SimpleObjectProperty<>();
        statusProperty.setValue(STOPPED);
    }

    @Override
    public void execute() {
        flushTrace();
    }

    private void startTrace() {
        if (state != State.STOPPED) {
            log.warn("could not start trace. tracing must be stopped");
        }
        try {
            String selectedType = selections.get(TRACER_TYPE).getSelected();
            if (selectedType.equals(TRACER_TYPE_FULL)) {
                scriptRunner.run(Scripts.START_FULL_TRACE, settings.get(bufferSize));
            } else if (selectedType.equals(TRACER_TYPE_THREAD_PRIORITIES)) {
                scriptRunner.run(Scripts.START_THREAD_PRIORITIES_TRACE, settings.get(bufferSize));
            } else {
                throw new ScriptException("unsupported tracer type: " + selectedType);
            }
            log.info("TRACER RUNNING");
            statusProperty.set(RUNNING);
            state = State.RUNNING;
        } catch (ScriptException e) {
            handleRunScirptException(e);
        }

    }

    private void stopTrace() {
        if (state != State.RUNNING) {
            log.warn("could not stop trace. tracing must not be stopped or writing");
        }
        scriptRunner.run(Scripts.STOP_TRACE);
        log.info("TRACER STOPPED");
        statusProperty.set(STOPPED);
        state = State.STOPPED;
    }

    private void flushTrace() {
        if (state != State.RUNNING) {
            log.warn("could not write trace. Please start tracing first");
            return;
        }
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    statusProperty.set(WRITING);
                    state = TracerRecorder.State.WRITING;
                    log.info("WRITING TRACE");
                    scriptRunner.run(Scripts.FLUSH_TRACE, getTraceFileName());
                    statusProperty.set(RUNNING);
                    state = TracerRecorder.State.RUNNING;
                    return null;
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error(e.toString());
                    return null;
                }
            }
        };

        new Thread(task).start();
    }

    private String getTraceFileName() {
        LocalDateTime time = LocalDateTime.now();
        return time.format(formatter) + ".etl";
    }

    private void handleRunScirptException(ScriptException exception) {
        if (exception.getCause() instanceof ErrorStreamParserException) {
            ErrorStreamParserException espe = (ErrorStreamParserException) exception.getCause();
            for(String error : espe.errors) {
                if (error.contains("0xb7")) {
                    log.warn(exception.getMessage());
                    log.warn("trace is already running");
                    statusProperty.set(RUNNING);
                    state = State.RUNNING;
                    return;
                }
            }
        }
        log.error(exception.getMessage());
        log.error("could not start trace. PLEASE NOTE THAT TRACING REQUIRES ADMIN PRIVILEGES");
    }

}
