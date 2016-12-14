package de.steinberg.rpc.execution.trigger.ui;

import de.steinberg.rpc.execution.trigger.core.annotations.DisplayName;
import de.steinberg.rpc.execution.trigger.core.engine.Control;
import de.steinberg.rpc.execution.trigger.core.engine.Controls;
import de.steinberg.rpc.execution.trigger.core.engine.Settings;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by lkleen on 11/29/2016.
 */
@Slf4j
@DisplayName("glitch monitor")
public class MonitorA extends MonitorMock {

    @Override
    public Settings getSettings() {
        Settings settings = new Settings();
        settings.put("path",null);
        settings.put("somethingelse",null);
        return settings;
    }

    @Override
    public Controls getControls() {
        Controls controls = new Controls();
        createController(controls, "foo");
        createController(controls, "bar");
        createController(controls, "baz");
        return controls;
    }

    private void createController(Controls controls, String str) {
        controls.put(str, new Control() {
            @Override
            public void setSettings(Settings settings) {

            }

            @Override
            public void trigger() {
                log.info(str);
            }
        });
    }

}
