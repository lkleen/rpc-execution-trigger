package org.larsworks.trading.data.collector.nasdaq.companies;

import org.larsworks.trading.data.collector.configuration.TradingDataCollectorConfiguration;
import org.larsworks.trading.data.collector.finance.nasdq.companies.csv.Company;
import org.larsworks.trading.data.collector.finance.nasdq.companies.csv.CompanyParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by lars on 19.03.2017.
 */
public class CompanyParserTest {

    @Test
    public void testParseCompanyList() {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                TradingDataCollectorConfiguration.class
        );
        CompanyParser parser = context.getBean(CompanyParser.class);
        InputStream input = getClass().getClassLoader().getSystemResourceAsStream("nasdaq-companylist.csv");
        Set<Company> companies = new TreeSet<>(parser.read(Company.class, input));
        //for (Company c : companies) {System.out.println (c);}
        Assert.assertEquals(6697, companies.size());
    }


}
