package org.larsworks.trading.data.collector.nasdaq.companies;

import org.larsworks.trading.data.collector.configuration.TradingDataCollectorConfiguration;
import org.larsworks.trading.data.collector.engine.query.generation.SelectQueryRange;
import org.larsworks.trading.data.collector.finance.nasdq.companies.csv.Company;
import org.larsworks.trading.data.collector.finance.yahoo.yql.CompanyQueryBuilder;
import org.larsworks.trading.data.collector.finance.yahoo.yql.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * Created by lars on 19.03.2017.
 */
public class CompanyQueryBuilderTest {

    @Test
    public void testCreateCompanyQueries() {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                TradingDataCollectorConfiguration.class
        );
        CompanyQueryBuilder queryBuilder = context.getBean(CompanyQueryBuilder.class);
        SelectQueryRange range = new SelectQueryRange(
                LocalDate.of(2007, 1, 1),
                LocalDate.of(2017, 3, 19)
        );
        Period maxAllowedQueryPeriod = Period.ofYears(1);

        Company company = new Company();
        company.setSymbol("XYZ");
        List<Query> queries = queryBuilder.createQuerysForCompany(company, range, maxAllowedQueryPeriod);

        Assert.assertEquals(11, queries.size());

    }

}
