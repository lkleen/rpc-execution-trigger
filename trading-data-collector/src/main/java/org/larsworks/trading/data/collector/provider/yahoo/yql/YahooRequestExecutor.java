package org.larsworks.trading.data.collector.provider.yahoo.yql;

import org.larsworks.trading.data.collector.engine.query.generation.Query;
import org.larsworks.trading.data.collector.engine.query.generation.SelectQueryResponse;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;

/**
 * Created by lars on 19.03.2017.
 */
public class YahooRequestExecutor implements RequestExecutor {

    final String endpoint = "https://query.yahooapis.com/v1/public/yql";

    final String prefix = "env 'store://datatables.org/alltableswithkeys'; ";

    final String formatString = "format=json";

    @Override
    public SelectQueryResponse execute(Query query) {
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(createURIFrom(endpoint, prefix, query), String.class);
        SelectQueryResponse response = new SelectQueryResponse();
        response.setJsonData(jsonString);
        return response;
    }

    @Override
    public URI createURIFrom(String endpoint, String prefix, Query query) {
        try {
            String urlEncodedString =
                    endpoint
                            + "?q="
                            + URLEncoder.encode(prefix + query.getString(), "UTF-8")
                            + "&" + formatString;
            return new URI(urlEncodedString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
