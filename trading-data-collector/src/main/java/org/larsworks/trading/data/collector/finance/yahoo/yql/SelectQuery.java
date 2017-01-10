package org.larsworks.trading.data.collector.finance.yahoo.yql;

/**
 * @author Lars Kleen
 * @since ?version
 *        Date: 08.01.17
 *        Time: 17:54
 */
public class SelectQuery implements Query {

    final String value;

    public SelectQuery(String value) {
        this.value = value;
    }

    @Override
    public String getString() {
        return value;
    }
}
