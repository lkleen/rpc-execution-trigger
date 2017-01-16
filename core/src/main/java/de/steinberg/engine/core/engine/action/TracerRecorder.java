package de.steinberg.engine.core.engine.action;

import de.steinberg.engine.core.annotations.DisplayName;
import de.steinberg.engine.core.exception.script.ErrorStreamParserException;
import de.steinberg.engine.core.exception.script.ScriptException;
import de.steinberg.engine.core.process.ScriptRunner;
import de.steinberg.engine.core.process.Scripts;
import lombok.extern.slf4j.Slf4j;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by LKLeen on 29.12.2016.
 */
@Slf4j
@DisplayName("Trace Recorder")
public class TracerRecorder extends AbstractAction {

    private enum State {STOPPED, RUNNING};

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd.HHmmss");

    State state = State.STOPPED;

    @Inject
    ScriptRunner scriptRunner;

    public TracerRecorder() {
        controls.put("start", () -> {startTrace();});
        controls.put("stop", () -> {stopTrace();});
        controls.put("flush", () -> {flushTrace();});
    }

    @Override
    public void execute() {
        flushTrace();
    }

    private void startTrace() {
        try {
            if (state == State.RUNNING) {
                log.warn ("trace already started");
                log.info("TRACER RUNNING");
                return;
            }
            scriptRunner.run(Scripts.START_TRACE_RECORDER);
            state = State.RUNNING;
            log.info("TRACER RUNNING");
        } catch (ScriptException e) {
            handleRunScirptException(e);
        }

    }

    private void stopTrace() {
        if (state == State.STOPPED) {
            log.warn("tracing is not running");
            return;
        }
        scriptRunner.run(Scripts.STOP_TRACE_RECORDER);
        state = State.STOPPED;
        log.info("TRACER STOPPED");
    }

    private void flushTrace() {
        if (state == State.STOPPED) {
            log.warn("could not write trace. Please start tracing first");
            return;
        }
        log.info("WRITING TRACE");
        scriptRunner.run(Scripts.FLUSH_TRACE_RECORDER, getTraceFileName());
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
                    log.warn("could not start trace. trace is already running");
                    state = State.RUNNING;
                    return;
                }
            }
        }
        log.error(exception.getMessage());
        log.error("could not start trace. PLEASE NOTE THAT TRACING REQUIRES ADMIN PRIVILEGES");
    }

}
