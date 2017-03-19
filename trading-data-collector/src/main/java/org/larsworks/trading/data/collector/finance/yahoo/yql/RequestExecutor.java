package org.larsworks.trading.data.collector.finance.yahoo.yql;

import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lars on 19.03.2017.
 */
public class RequestExecutor {

    final String endpoint = "https://query.yahooapis.com/v1/public/yql";

    final String prefix = "env 'store://datatables.org/alltableswithkeys'; ";

    final String formatString = "format=json";

    public SelectQueryResponse execute(Query query) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> parameters = new HashMap<>();
        String test = restTemplate.getForObject(createURIFrom(endpoint, prefix, query), String.class);
        return new SelectQueryResponse();
    }

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
