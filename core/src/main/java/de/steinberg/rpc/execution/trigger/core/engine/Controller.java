package de.steinberg.rpc.execution.trigger.core.engine;

/**
 * Created by lkleen on 11/29/2016.
 */
public interface Controller {
    void setSettings(Settings settings);
    void trigger();
}
