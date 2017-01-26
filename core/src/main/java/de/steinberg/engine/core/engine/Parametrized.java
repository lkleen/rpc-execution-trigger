package de.steinberg.engine.core.engine;

import de.steinberg.engine.core.engine.control.ControlsAware;
import de.steinberg.engine.core.engine.selection.SelectionsAware;
import de.steinberg.engine.core.engine.setting.SettingsAware;
import de.steinberg.engine.core.engine.status.StatusAware;

/**
 * Created by lars on 24.01.2017.
 */
public interface Parametrized extends ControlsAware, SelectionsAware, SettingsAware, StatusAware {
}
