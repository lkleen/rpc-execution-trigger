package de.steinberg.rpc.execution.trigger.ui;

import de.steinberg.rpc.execution.trigger.core.annotations.DisplayName;
import de.steinberg.rpc.execution.trigger.core.engine.Controller;
import de.steinberg.rpc.execution.trigger.core.engine.Controllers;
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
    public Controllers getControllers () {
        Controllers controllers = new Controllers();
        createController(controllers, "foo");
        createController(controllers, "bar");
        createController(controllers, "baz");
        return controllers;
    }

    private void createController(Controllers controllers, String str) {
        controllers.put(str, new Controller() {
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
