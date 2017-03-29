package org.larsworks.trading.data.collector.finance.yahoo.yql;

import org.larsworks.trading.data.collector.configuration.TradingDataCollectorConfiguration;
import org.larsworks.trading.data.collector.engine.query.generation.SelectQueryParameters;
import org.larsworks.trading.data.repository.Evaluator;
import org.larsworks.trading.data.repository.Repository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by LKLeen on 29.03.2017.
 */
public class RepositoryEvaluatorTest {

    @Test
    public void testNoCompanieEntry() {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                TradingDataCollectorConfiguration.class
        );
        Evaluator evaluator = context.getBean(Evaluator.class);

        Repository repository = new Repository();
        LocalDate start = LocalDate.of(2001, 1, 1);
        LocalDate end = LocalDate.of(2002, 1, 1);
        String symbol = "ABCD";

        List<SelectQueryParameters> list = evaluator.getMissingEntries(repository, start, end, symbol);
        SelectQueryParameters params = list.get(0);
        Assert.assertEquals(symbol, params.getSymbol());
        Assert.assertEquals(start, params.getRange().getStartDate());
        Assert.assertEquals(end, params.getRange().getEndDate());
    }

}
