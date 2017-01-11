package org.larsworks.trading.data.collector.finance.yahoo.yql;

import org.larsworks.trading.data.collector.exception.InvalidRangeException;
import org.larsworks.trading.data.collector.exception.QueryBuilderException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

/**
 * @author Lars Kleen
 * @since ?version
 *        Date: 08.01.17
 *        Time: 18:00
 */
public class SelectQueryRange {

    final LocalDate startDate;
    final LocalDate endDate;

    public SelectQueryRange(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Period getPeriod() {
        if (startDate == null || endDate == null) {
            throw new InvalidRangeException("startDate or endDate == null");
        }
        return startDate.until(endDate);
    }

}
