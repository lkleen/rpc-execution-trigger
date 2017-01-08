package org.larsworks.trading.data.collector.engine.monitor;

import de.steinberg.engine.core.engine.monitor.AbstractAsyncMonitor;

/**
 * @author Lars Kleen
 * @since ?version
 *        Date: 08.01.17
 *        Time: 17:41
 */
public class TriggerCollectionMonitor extends AbstractAsyncMonitor {
    @Override
    public boolean conditionFulfilled() {
        return true;
    }
}
