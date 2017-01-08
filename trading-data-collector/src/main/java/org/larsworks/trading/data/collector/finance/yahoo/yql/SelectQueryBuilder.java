package org.larsworks.trading.data.collector.finance.yahoo.yql;

import org.larsworks.trading.data.collector.exception.QueryBuilderException;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

/**
 * @author Lars Kleen
 * @since ?version
 *        Date: 08.01.17
 *        Time: 17:54
 */
public class SelectQueryBuilder implements QueryBuilder<SelectQueryParameters> {

    static final String PREFIX = "select * from";

    @Override
    public Query build(SelectQueryParameters parameters) {
        validateParameters(parameters);
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void validateParameters(SelectQueryParameters parameters) {
        if (parameters == null) {
            throw new QueryBuilderException("parameters == null");
        }
        if (parameters.range == null || parameters.symbol == null) {
            throw new QueryBuilderException("parameter with null value is not allowed");
        }
        if (parameters.symbol.length() == 0) {
            throw new QueryBuilderException("empty symbol not allowed");
        }
        if (parameters.range.getValue(ChronoUnit.DAYS) <= 0) {
            throw new QueryBuilderException("query range <= 0 days");
        }
    }
}
