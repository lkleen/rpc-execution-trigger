package de.steinberg.rpc.execution.trigger.core.engine;

/**
 * provides getters and setters for settings
 *
 * Created by lkleen on 11/29/2016.
 */
public interface SettingsAware {

    void setSettings(Settings settings);
    Settings getSettings();

}
