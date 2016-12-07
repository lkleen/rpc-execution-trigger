package de.steinberg.rpc.execution.trigger.ui;

import de.steinberg.rpc.execution.trigger.core.annotations.DisplayName;
import de.steinberg.rpc.execution.trigger.core.engine.Settings;

/**
 * Created by lkleen on 11/29/2016.
 */
@DisplayName("glitch monitor")
public class MonitorA extends MonitorMock {

    @Override
    public Settings getSettings() {
        Settings settings = new Settings();
        settings.put("path",null);
        settings.put("somethingelse",null);
        return settings;
    }

}
