package de.steinberg.engine.core.engine;

import de.steinberg.engine.core.engine.control.Controls;
import de.steinberg.engine.core.engine.selection.Selections;
import de.steinberg.engine.core.engine.setting.Settings;

/**
 * Created by lars on 24.01.2017.
 */
public abstract class DefaultParametrized implements Parametrized {

    protected final Controls controls = new Controls();
    protected final Selections selections = new Selections();
    protected final Settings settings = new Settings();



    @Override
    public final Controls getControls() {
        return controls;
    }

    @Override
    public final Selections getSelections() {
        return selections;
    }

    @Override
    public final Settings getSettings() {
        return settings;
    }
}
