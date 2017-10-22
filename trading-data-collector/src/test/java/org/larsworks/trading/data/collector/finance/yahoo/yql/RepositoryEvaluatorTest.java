package org.larsworks.trading.data.collector.finance.yahoo.yql;

import org.larsworks.trading.data.collector.configuration.TradingDataCollectorConfiguration;
import org.larsworks.trading.data.collector.repository.Evaluator;
import org.larsworks.trading.data.collector.repository.Quote;
import org.larsworks.trading.data.collector.repository.Repository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LKLeen on 29.03.2017.
 */
public class RepositoryEvaluatorTest {

    @Test
    public void testNoCompanyEntry() {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                TradingDataCollectorConfiguration.class
        );
        Evaluator evaluator = context.getBean(Evaluator.class);

        Repository repository = new Repository();
        LocalDate start = LocalDate.of(2001, 1, 1);
        LocalDate end = LocalDate.of(2002, 1, 1);
        String symbol = "ABCD";

        List<Evaluator.CompanyQueryData> list = evaluator.getMissingEntries(repository, start, end, symbol);
        Evaluator.CompanyQueryData params = list.get(0);
        Assert.assertEquals(symbol, params.company.getSymbol());
        Assert.assertEquals(start, params.before.getStartDate());
        Assert.assertEquals(end, params.before.getEndDate());
        Assert.assertEquals(null, params.after);
    }

    @Test
    public void testCompanyWithSingleQuoteEntry() {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                TradingDataCollectorConfiguration.class
        );
        Evaluator evaluator = context.getBean(Evaluator.class);

        Repository repository = new Repository();
        LocalDate repoStart = LocalDate.of(2001, 1, 1);
        LocalDate repoEnd   = LocalDate.of(2005, 1, 1);
        LocalDate companyStart = LocalDate.of(2003, 1, 1);
        LocalDate companyEnd   = LocalDate.of(2004, 1, 1);

        String symbol = "ABCD";
        org.larsworks.trading.data.collector.repository.Company company = createCompanyWithQuotes(symbol, companyStart, companyEnd);
        List<org.larsworks.trading.data.collector.repository.Company> companies = new ArrayList<>();
        companies.add(company);
        repository.setCompanies(companies);

        List<Evaluator.CompanyQueryData> list = evaluator.getMissingEntries(repository, repoStart, repoEnd, symbol);

        Assert.assertEquals(1, list.size());
        Evaluator.CompanyQueryData queryData = list.get(0);
        Assert.assertEquals(symbol, queryData.company.getSymbol());
        Assert.assertEquals(repoStart, queryData.before.getStartDate());
        Assert.assertEquals(companyStart, queryData.before.getEndDate());
        Assert.assertEquals(companyEnd, queryData.after.getStartDate());
        Assert.assertEquals(repoEnd, queryData.after.getEndDate());
    }

    private org.larsworks.trading.data.collector.repository.Company createCompanyWithQuotes(String symbol, LocalDate start, LocalDate end) {
        org.larsworks.trading.data.collector.repository.Company company = new org.larsworks.trading.data.collector.repository.Company();
        company.setSymbol(symbol);
        List<Quote> quotes = new ArrayList<>();
        company.setQuotes(quotes);
        Quote startQuote = new Quote();
        Quote endQuote = new Quote();
        startQuote.setDate(start);
        endQuote.setDate(end);
        quotes.add(startQuote);
        quotes.add(endQuote);
        return company;
    }

}
