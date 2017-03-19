package org.larsworks.trading.data.collector.finance.yahoo.yql.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by lars on 19.03.2017.
 */
@Data
public class Quote {

    @JsonProperty("Symbol")
    String symbol   ;

    @JsonProperty("Date")
    String date     ;

    @JsonProperty("Open")
    String open     ;

    @JsonProperty("High")
    String high     ;

    @JsonProperty("Low")
    String low      ;

    @JsonProperty("Close")
    String close    ;

    @JsonProperty("Volume")
    String volume   ;

    @JsonProperty("Adj_Close")
    String adjClose;

}
