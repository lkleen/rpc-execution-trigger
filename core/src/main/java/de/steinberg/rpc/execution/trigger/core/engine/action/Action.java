package de.steinberg.rpc.execution.trigger.core.engine.action;

import de.steinberg.rpc.execution.trigger.core.engine.ControlsAware;
import de.steinberg.rpc.execution.trigger.core.engine.SettingsAware;

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
