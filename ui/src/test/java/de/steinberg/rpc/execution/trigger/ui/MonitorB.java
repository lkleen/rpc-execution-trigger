package de.steinberg.rpc.execution.trigger.ui;

import de.steinberg.rpc.execution.trigger.core.annotations.DisplayName;
import de.steinberg.rpc.execution.trigger.core.engine.Settings;

/**
 * Created by lkleen on 11/29/2016.
 */
@DisplayName("awkward monitor")
public class MonitorB extends MonitorMock {

    @Override
    public Settings getSettings() {
        Settings settings = new Settings();
        settings.put("schnick",null);
        settings.put("schnack",null);
        return settings;
    }

}
