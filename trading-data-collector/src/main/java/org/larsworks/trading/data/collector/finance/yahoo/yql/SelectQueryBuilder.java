package org.larsworks.trading.data.collector.finance.yahoo.yql;

import org.larsworks.trading.data.collector.exception.QueryBuilderException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author Lars Kleen
 * @since ?version
 *        Date: 08.01.17
 *        Time: 17:54
 */
public class SelectQueryBuilder implements QueryBuilder<SelectQueryParameters> {

    static final String PREFIX = "select * from yahoo.finance.historicaldata where symbol =";

    @Override
    public Query build(SelectQueryParameters parameters) {
        validateParameters(parameters);
        String startDate = formatDate("startDate", parameters.range.startDate);
        String endDate   = formatDate("endDate", parameters.range.endDate);
        return new SelectQuery(PREFIX + " \""+parameters.symbol+"\" and " + startDate + " and " + endDate);
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
        Period period = parameters.range.getPeriod();
        if (period.isNegative() || period.isZero()) {
            throw new QueryBuilderException("query range <= 0 days");
        }
    }

    private String formatDate(String prefix, LocalDate date) {
        return prefix + " = \"" + date.format(DateTimeFormatter.ISO_LOCAL_DATE) + "\"";
    }
}
