package org.larsworks.trading.data.collector.finance.yahoo.yql;

/**
 * @author Lars Kleen
 * @since ?version
 *        Date: 08.01.17
 *        Time: 17:53
 */
public interface QueryBuilder<Parameters> {
    Query build(Parameters parameters);
}
