package org.larsworks.trading.data.collector.engine.monitor;

import de.steinberg.engine.core.annotations.TooltipText;
import de.steinberg.engine.core.engine.control.Control;
import de.steinberg.engine.core.engine.monitor.AbstractAsyncMonitor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.spi.LocationAwareLogger;

/**
 * @author Lars Kleen
 * @since ?version
 *        Date: 08.01.17
 *        Time: 17:41
 */
@Slf4j
public class TriggerCollectionMonitor extends AbstractAsyncMonitor {

    @TooltipText("starts trading data collection")
    private class Start implements Control {
        @Override
        public void trigger() {
            log.info("data collection started");
            started = true;
        }
    }

    @TooltipText("stops trading data collection")
    private class Stop implements Control {
        @Override
        public void trigger() {
            log.info("data collection stopped");
            started = false;
        }
    }

    boolean started = false;

    public TriggerCollectionMonitor() {
        controls.put("start", new Start());
        controls.put("stop", new Stop());
    }

    @Override
    public boolean conditionFulfilled() {
        return started;
    }
}
