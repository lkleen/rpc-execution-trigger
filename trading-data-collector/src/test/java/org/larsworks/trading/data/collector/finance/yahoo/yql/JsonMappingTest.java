package org.larsworks.trading.data.collector.finance.yahoo.yql;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Assert;
import org.larsworks.trading.data.collector.configuration.TradingDataCollectorConfiguration;
import org.larsworks.trading.data.collector.finance.yahoo.yql.json.Response;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;

import java.io.InputStream;

/**
 * Created by lars on 19.03.2017.
 */
public class JsonMappingTest {

    @Test
    public void testObjectMapper() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                TradingDataCollectorConfiguration.class
        );

        ObjectMapper mapper = context.getBean(ObjectMapper.class);
        InputStream input = getClass().getClassLoader().getSystemResourceAsStream("yql-query-response.json");
        Response response = mapper.readValue(input, Response.class);
        Assert.assertNotNull(response);
    }

}
