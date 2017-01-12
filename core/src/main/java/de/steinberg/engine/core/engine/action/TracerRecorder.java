package de.steinberg.engine.core.engine.action;

import de.steinberg.engine.core.annotations.DisplayName;
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
        scriptRunner.run(Scripts.START_TRACE_RECORDER);
    }

    private void stopTrace() {
        scriptRunner.run(Scripts.STOP_TRACE_RECORDER);
    }

    private void flushTrace() {
        scriptRunner.run(Scripts.FLUSH_TRACE_RECORDER, getTraceFileName());
    }

    private String getTraceFileName() {
        LocalDateTime time = LocalDateTime.now();
        return time.format(DateTimeFormatter.ISO_DATE_TIME) + ".etl";
    }

}
