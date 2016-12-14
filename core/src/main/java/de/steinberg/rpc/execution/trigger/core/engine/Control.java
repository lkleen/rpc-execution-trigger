package de.steinberg.rpc.execution.trigger.core.engine;

/**
 * Created by lkleen on 11/29/2016.
 */
public interface Control {
    void setSettings(Settings settings);
    void trigger();
}
