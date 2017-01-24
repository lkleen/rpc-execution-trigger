package de.steinberg.engine.ui.test;

import de.steinberg.engine.core.annotations.DisplayName;
import de.steinberg.engine.core.engine.control.Control;
import de.steinberg.engine.core.engine.control.Controls;
import de.steinberg.engine.core.engine.monitor.Monitor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by lkleen on 11/29/2016.
 */
@Slf4j
@DisplayName("glitch monitor")
public class MonitorA extends MonitorMock {

    public MonitorA() {
        settings.put( () -> "path",null);
        settings.put( () -> "somethingelse",null);
    }

    @Override
    public Controls getControls() {
        Controls controls = new Controls();
        createTrigger(controls, this);
        createController(controls, "foo");
        createController(controls, "bar");
        createController(controls, "baz");
        return controls;
    }

    private void createTrigger (Controls controls, Monitor monitor) {
        controls.put("trigger", () -> {monitor.run();});
    }

    private void createController(Controls controls, String str) {
        controls.put(str, new Control() {
            @Override
            public void trigger() {
                log.info(str);
            }
        });
    }

}
