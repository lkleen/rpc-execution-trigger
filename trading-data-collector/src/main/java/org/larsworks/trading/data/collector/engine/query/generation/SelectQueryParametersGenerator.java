package org.larsworks.trading.data.collector.engine.query.generation;

import java.util.List;

/**
 * Created by admin on 28.01.2017.
 */
public interface SelectQueryParametersGenerator {

    void setSymbols(List<String> symbols);
    void setRange(SelectQueryRange range);

    /**
     * generates the query parameters for the next query
     * @return
     */
    SelectQueryParameters generateNext();

}
