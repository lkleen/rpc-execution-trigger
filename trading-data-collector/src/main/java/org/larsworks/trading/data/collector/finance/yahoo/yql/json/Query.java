package org.larsworks.trading.data.collector.finance.yahoo.yql.json;

import lombok.Data;

import java.util.List;

/**
 * Created by lars on 19.03.2017.
 */
@Data
public class Query {

    @Data
    static class Results
    {
        List<Quote> quote;
    }

    int count;
    String created;
    String lang;
    Results results;

}
