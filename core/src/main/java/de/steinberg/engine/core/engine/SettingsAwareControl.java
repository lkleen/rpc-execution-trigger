package de.steinberg.engine.core.engine;

/**
 * Created by LKLeen on 10.01.2017.
 */
public abstract class SettingsAwareControl implements SettingsAware, Control {

    final Settings settings;

    public SettingsAwareControl(Settings settings) {
        this.settings = settings;
    }

    @Override
    public Settings getSettings() {
        return null;
    }
}
