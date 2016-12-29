package de.steinberg.rpc.execution.trigger.core.engine.monitor;

import de.steinberg.rpc.execution.trigger.core.engine.monitor.AbstractAsyncMonitor;

/**
 * Created by lkleen on 12/14/2016.
 */
public class GlitchMonitor extends AbstractAsyncMonitor {

    @Override
    public boolean conditionFulfilled() {
        return false;
    }
}
