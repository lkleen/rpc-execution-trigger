package de.steinberg.rpc.execution.trigger.ui;

import de.steinberg.rpc.execution.trigger.core.engine.Control;
import de.steinberg.rpc.execution.trigger.core.engine.Controls;
import de.steinberg.rpc.execution.trigger.core.engine.Settings;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by lkleen on 11/29/2016.
 */
@Slf4j
public class ActionA extends ActionMock {
    @Override
    public Controls getControls() {
        Controls controls = new Controls();
        controls.put("action control a", new Control() {
            @Override
            public void setSettings(Settings settings) {

            }

            @Override
            public void trigger() {
                log.info("action control a triggered");
            }
        });
        return controls;
    }

    @Override
    public Settings getSettings() {
        Settings settings = new Settings();
        settings.put("schni","this");
        settings.put("schna","is");
        settings.put("schnuck","text");
        return settings;
    }

}
