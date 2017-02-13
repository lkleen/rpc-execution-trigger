package org.larsworks.trading.data.collector.engine.query.generation;

import lombok.Data;
import org.larsworks.trading.data.collector.exception.InvalidRangeException;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author Lars Kleen
 * @since ?version
 *        Date: 08.01.17
 *        Time: 18:00
 */
@Data
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
        Period period = startDate.until(endDate);
        return period;
    }

}
