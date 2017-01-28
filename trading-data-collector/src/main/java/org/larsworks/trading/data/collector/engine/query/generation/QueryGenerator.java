package org.larsworks.trading.data.collector.engine.query.generation;

import org.larsworks.trading.data.collector.finance.yahoo.yql.Query;

/**
 * Created by admin on 28.01.2017.
 */
public interface QueryGenerator {
    Query getNext();
}
