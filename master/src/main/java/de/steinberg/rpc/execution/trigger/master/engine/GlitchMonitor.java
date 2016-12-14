package de.steinberg.rpc.execution.trigger.master.engine;

import de.steinberg.rpc.execution.trigger.core.engine.AbstractAsyncMonitor;
import de.steinberg.rpc.execution.trigger.core.engine.Action;

/**
 * Created by lkleen on 12/14/2016.
 */
public class GlitchMonitor extends AbstractAsyncMonitor {

    @Override
    public boolean conditionFulfilled() {
        return false;
    }
}
