package org.larsworks.trading.data.collector.finance.yahoo.yql;

import org.larsworks.trading.data.collector.configuration.TradingDataCollectorConfiguration;
import org.larsworks.trading.data.collector.engine.query.generation.SelectQueryRange;
import org.larsworks.trading.data.collector.finance.nasdq.companies.csv.Company;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * Created by lars on 19.03.2017.
 */
public class RequerstExecutorTest {

    @Test
    public void testExecuteRequest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                TradingDataCollectorConfiguration.class
        );
        CompanyQueryBuilder queryBuilder = context.getBean(CompanyQueryBuilder.class);
        SelectQueryRange range = new SelectQueryRange(
                LocalDate.of(2017, 1, 1),
                LocalDate.of(2017, 2, 1)
        );
        Period maxAllowedQueryPeriod = Period.ofYears(1);

        Company company = new Company();
        company.setSymbol("YHOO");
        List<Query> queries = queryBuilder.createQuerysForCompany(company, range, maxAllowedQueryPeriod);

        RequestExecutor requestExecutor = context.getBean(RequestExecutor.class);
        requestExecutor.execute(queries.get(0));

    }


}
