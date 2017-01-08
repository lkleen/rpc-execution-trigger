package org.larsworks.trading.data.collector.configuration;

import de.steinberg.engine.core.engine.Engine;
import org.larsworks.trading.data.collector.engine.TradingDataCollectorEngine;
import org.larsworks.trading.data.collector.engine.action.CollectDataAction;
import org.larsworks.trading.data.collector.engine.monitor.TriggerCollectionMonitor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author Lars Kleen
 * @since ?version
 *        Date: 08.01.17
 *        Time: 17:35
 */
@Configuration
public class TradingDataCollectorConfiguration {

    @Bean
    public TradingDataCollectorEngine tradingDataCollectorEngine() {
        TradingDataCollectorEngine engine = new TradingDataCollectorEngine();
        engine.addMonitor(triggerCollectionMonitor());
        return engine;
    }

    @Bean
    public TriggerCollectionMonitor triggerCollectionMonitor() {
        TriggerCollectionMonitor monitor = new TriggerCollectionMonitor();
        monitor.setInterval(2, TimeUnit.SECONDS); // to limit request to < 2000 per hour
        monitor.addAction(collectDataAction());
        return monitor;
    }

    @Bean
    public CollectDataAction collectDataAction() {
        return new CollectDataAction();
    }

}
