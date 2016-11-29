package de.steinberg.rpc.execution.trigger.core.engine;

/**
 * Created by lkleen on 11/28/2016.
 */
public interface Action extends SettingsAware {

    void execute();

}
