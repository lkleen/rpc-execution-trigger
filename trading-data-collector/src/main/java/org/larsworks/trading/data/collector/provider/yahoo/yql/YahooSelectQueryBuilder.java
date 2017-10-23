package org.larsworks.trading.data.collector.provider.yahoo.yql;

import org.larsworks.trading.data.collector.engine.query.generation.Query;
import org.larsworks.trading.data.collector.engine.query.generation.QueryBuilder;
import org.larsworks.trading.data.collector.engine.query.generation.SelectQuery;
import org.larsworks.trading.data.collector.engine.query.generation.SelectQueryParameters;
import org.larsworks.trading.data.collector.exception.QueryBuilderException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * @author Lars Kleen
 * @since ?version
 *        Date: 08.01.17
 *        Time: 17:54
 */
public class YahooSelectQueryBuilder implements QueryBuilder<SelectQueryParameters> {

    static final String PREFIX = "select * from yahoo.finance.historicaldata where symbol =";

    @Override
    public Query build(SelectQueryParameters parameters) {
        validateParameters(parameters);
        String startDate = formatDate("startDate", parameters.getRange().getStartDate());
        String endDate   = formatDate("endDate", parameters.getRange().getEndDate());
        return new SelectQuery(PREFIX + " \""+parameters.getSymbol()+"\" and " + startDate + " and " + endDate);
    }

    public void validateParameters(SelectQueryParameters parameters) {
        if (parameters == null) {
            throw new QueryBuilderException("parameters == null");
        }
        if (parameters.getRange() == null || parameters.getSymbol() == null) {
            throw new QueryBuilderException("parameter with null value is not allowed");
        }
        if (parameters.getSymbol().length() == 0) {
            throw new QueryBuilderException("empty symbol not allowed");
        }
        Period period = parameters.getRange().getPeriod();
        if (period.isNegative() || period.isZero()) {
            throw new QueryBuilderException("query range <= 0 days");
        }
    }

    private String formatDate(String prefix, LocalDate date) {
        return prefix + " = \"" + date.format(DateTimeFormatter.ISO_LOCAL_DATE) + "\"";
    }
}
