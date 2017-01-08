package org.larsworks.trading.data.collector.configuration;

import org.larsworks.trading.data.collector.engine.TradingDataCollectorEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        return new TradingDataCollectorEngine();
    }

}
