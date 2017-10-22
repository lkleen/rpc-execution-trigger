package org.larsworks.trading.data.collector.finance.yahoo.yql;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Assert;
import org.larsworks.trading.data.collector.TestUtils;
import org.larsworks.trading.data.collector.configuration.TradingDataCollectorConfiguration;
import org.larsworks.trading.data.collector.finance.yahoo.yql.json.RepositoryAppender;
import org.larsworks.trading.data.collector.finance.yahoo.yql.json.Response;
import org.larsworks.trading.data.collector.repository.Repository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;

import java.io.InputStream;

/**
 * Created by LKLeen on 29.03.2017.
 */
public class RepositoryAppenderTest {

    @Test
    public void testWriteResponseToRepository() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                TradingDataCollectorConfiguration.class
        );
        Repository repository = TestUtils.createRepositoryFromSingleResponse(context, "yql-query-response.json");
        Assert.assertEquals(1, repository.getCompanies().size());
        Assert.assertEquals(4, repository.getCompanies().get(0).getQuotes().size());
    }
}
