package de.steinberg.engine.ui.test;

import de.steinberg.engine.core.annotations.DisplayName;
import de.steinberg.engine.core.engine.control.Control;
import de.steinberg.engine.core.engine.control.Controls;
import de.steinberg.engine.core.engine.monitor.Monitor;
import de.steinberg.engine.core.engine.status.Status;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by lkleen on 11/29/2016.
 */
@Slf4j
@DisplayName("glitch monitor")
public class MonitorA extends MonitorMock {

    public MonitorA() {
        settings.put(() -> "path", null);
        settings.put(() -> "somethingelse", null);

        status = new Status(Status.Color.GREEN, "everything is fine");

        createTrigger(controls, this);
        createController(controls, Status.Color.GREEN, "run", "running");
        createController(controls, Status.Color.YELLOW, "process", "processing");
        createController(controls, Status.Color.RED, "stop", "stopped");
    }

    private void createTrigger (Controls controls, Monitor monitor) {
        controls.put("trigger", () -> {monitor.run();});
    }

    private void createController(Controls controls, Status.Color color, String buttonLabel, String str) {
        controls.put(buttonLabel, () -> {
            status.set(new Status(color, str));
        });
    }

}
