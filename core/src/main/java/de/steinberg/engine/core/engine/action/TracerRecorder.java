package de.steinberg.engine.core.engine.action;

import de.steinberg.engine.core.annotations.DisplayName;
import lombok.extern.slf4j.Slf4j;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by LKLeen on 29.12.2016.
 */
@Slf4j
@DisplayName("Trace Recorder")
public class TracerRecorder extends AbstractAction {

    private enum State {STOPPED, RUNNING};

    State state = State.STOPPED;

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
        throw new NotImplementedException();
    }

    private void stopTrace() {
        throw new NotImplementedException();
    }

    private void flushTrace() {
        throw new NotImplementedException();
    }

}
