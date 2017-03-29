package org.larsworks.trading.data.collector.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.steinberg.engine.core.parser.csv.CsvObjectMapper;
import org.larsworks.trading.data.collector.engine.TradingDataCollectorEngine;
import org.larsworks.trading.data.collector.engine.action.CollectDataAction;
import org.larsworks.trading.data.collector.engine.monitor.TriggerCollectionMonitor;
import org.larsworks.trading.data.collector.engine.query.generation.PeriodicalSelectQueryParametersGenerator;
import org.larsworks.trading.data.collector.finance.nasdaq.companies.csv.CompanyParser;
import org.larsworks.trading.data.collector.finance.yahoo.yql.CompanyQueryBuilder;
import org.larsworks.trading.data.collector.finance.yahoo.yql.RequestExecutor;
import org.larsworks.trading.data.collector.finance.yahoo.yql.SelectQueryBuilder;
import org.larsworks.trading.data.collector.finance.yahoo.yql.json.RepositoryWriter;
import org.larsworks.trading.data.repository.Evaluator;
import org.larsworks.trading.data.repository.Repository;
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
    public CompanyParser companyParser() {
        CompanyParser parser = new CompanyParser();
        parser.setMapper(new CsvObjectMapper());
        return parser;
    }

    @Bean
    public CompanyQueryBuilder companyQueryBuilder() {
        return new CompanyQueryBuilder();
    }

    @Bean
    public PeriodicalSelectQueryParametersGenerator periodicalSelectQueryParametersGenerator() {
        return new PeriodicalSelectQueryParametersGenerator();
    }

    @Bean
    public SelectQueryBuilder selectQueryBuilder() {
        return new SelectQueryBuilder();
    }

    @Bean
    public RequestExecutor requestExecutor() {
        return new RequestExecutor();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper;
    }

    @Bean
    public CollectDataAction collectDataAction() {
        return new CollectDataAction();
    }

    @Bean
    public Repository repository() {
        return new Repository();
    }

    @Bean
    public RepositoryWriter repositoryWriter() {
        return new RepositoryWriter();
    }

    @Bean
    public Evaluator evaluator() {
        return new Evaluator();
    }
}
