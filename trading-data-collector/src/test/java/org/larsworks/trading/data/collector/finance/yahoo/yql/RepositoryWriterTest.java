package org.larsworks.trading.data.collector.finance.yahoo.yql;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Assert;
import org.larsworks.trading.data.collector.configuration.TradingDataCollectorConfiguration;
import org.larsworks.trading.data.collector.finance.yahoo.yql.json.RepositoryWriter;
import org.larsworks.trading.data.collector.finance.yahoo.yql.json.Response;
import org.larsworks.trading.data.repository.Repository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;

import java.io.InputStream;

/**
 * Created by LKLeen on 29.03.2017.
 */
public class RepositoryWriterTest {

    @Test
    public void testWriteResponseToRepository() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                TradingDataCollectorConfiguration.class
        );
        Repository repository = context.getBean(Repository.class);
        RepositoryWriter writer = context.getBean(RepositoryWriter.class);
        Response response = getResponse(context);
        writer.store(repository, response);
        Assert.assertEquals(1, repository.getCompanies().size());
        Assert.assertEquals(4, repository.getCompanies().get(0).getQuotes().size());
    }

    private Response getResponse(ApplicationContext context) throws Exception {
        ObjectMapper mapper = context.getBean(ObjectMapper.class);
        InputStream input = getClass().getClassLoader().getSystemResourceAsStream("yql-query-response.json");
        return mapper.readValue(input, Response.class);
    }
}
