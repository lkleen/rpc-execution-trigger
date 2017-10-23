package org.larsworks.trading.data.collector.provider.yahoo.yql;

import org.larsworks.trading.data.collector.engine.query.generation.Query;
import org.larsworks.trading.data.collector.engine.query.generation.SelectQueryResponse;

import java.net.URI;

/**
 * @author Lars Kleen
 * @since ?version
 *        Date: 23.10.17
 *        Time: 16:32
 */
public interface RequestExecutor {
    SelectQueryResponse execute(Query query);

    URI createURIFrom(String endpoint, String prefix, Query query);
}
