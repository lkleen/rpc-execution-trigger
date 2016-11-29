package de.steinberg.rpc.execution.trigger.core.engine;

/**
 * Created by lkleen on 11/29/2016.
 */
public abstract class AbstractAction implements Action {

    Settings settings = new Settings();

    @Override
    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    @Override
    public Settings getSettings() {
        return settings;
    }
}