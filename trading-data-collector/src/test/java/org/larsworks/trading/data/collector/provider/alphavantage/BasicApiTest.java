package org.larsworks.trading.data.collector.provider.alphavantage;

import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import java.net.URI;

/**
 * getting familiar to alphavantage api
 */
public class BasicApiTest {

    final String endpoint = "http://www.alphavantage.co/query?";
    final String query = "function=TIME_SERIES_DAILY_ADJUSTED&symbol=AAP&outputsize=full&apikey=5R7I2U4PNX5RZBYV";

    @Test
    public void testApi() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI(endpoint + query);
        String jsonString = restTemplate.getForObject(uri, String.class);
        System.out.print(jsonString);
    }

}
