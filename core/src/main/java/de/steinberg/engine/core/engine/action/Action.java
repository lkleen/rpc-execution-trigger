package de.steinberg.engine.core.engine.action;

import de.steinberg.engine.core.engine.control.ControlsAware;
import de.steinberg.engine.core.engine.setting.SettingsAware;

/**
 * engine -> monitors -> listeners -> ACTIONS
 *
 * an action is executed from a listener which is triggered from a monitor
 *
 * Created by lkleen on 11/28/2016.
 */
public interface Action extends SettingsAware, ControlsAware {

    void execute();

}
