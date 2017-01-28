package org.larsworks.trading.data.collector.engine.query.generation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * generates queries for each symbol which are split to ranges of the given period
 * eg. symbols = "A,B"; range is 4 days; period is 2 days would generate 4 queries
 * a:d1-2,a:d3-4
 * b:d1-2,b:d3-4
 */
public class PeriodicalSelectQueryParametersGenerator implements SelectQueryParametersGenerator {

    @Getter
    @Setter
    private Period period;

    private List<String> symbols;

    private SelectQueryRange range;

    private String currentSymbol;
    private SelectQueryRange currentSubRange;

    private List<SelectQueryParameters> parameters;

    private int currentQueryIndex = 0;

    @Override
    public void setSymbols(List<String> symbols) {
        this.symbols = symbols;
    }

    @Override
    public void setRange(SelectQueryRange range) {
        this.range = range;
    }

    @Override
    public SelectQueryParameters generateNext() {
        if (parameters == null) {
            parameters = generateParameters(symbols, range, period);
        }
        if (currentQueryIndex == parameters.size()) {return null;}
        return parameters.get(currentQueryIndex++);
    }

    public List<SelectQueryParameters> generateParameters(List<String> symbols, SelectQueryRange range, Period period) {
        List<SelectQueryParameters> parameters = new ArrayList<>();
        for (String symbol : symbols) {
            for (SelectQueryRange subRange : splitRanges(period, range)) {
                parameters.add(new SelectQueryParameters(symbol, subRange));
            }
        }
        return parameters;
    }

    /**
     * splits the range of start and end day for start and end date to x ranges
     * with a size of period. If the range is split the last day of the input range is exclusive.
     * eg. a range fom 1990/01/01 to 1992/01/01 is split with a period of 1 year ti will include the ranges
     * 1990/01/01 to 1990/12/31
     * and
     * 1991/01/01 to 1991/12/31
     *
     * @param period the period to split the range to
     * @param range the overall range (with the last day excluded)
     * @return
     */
    public List<SelectQueryRange> splitRanges(Period period, SelectQueryRange range) {
        List<SelectQueryRange> ranges = new ArrayList<>();
        if (compare(range.getPeriod(), period) < 0) {
            ranges.add(new SelectQueryRange(range.getStartDate(), range.getEndDate().minus(Period.ofDays(1))));
            return  ranges;
        }

        final LocalDate finalEndDate = range.getEndDate();
        LocalDate startDate = range.getStartDate();

        while (startDate.isBefore(finalEndDate)) {
            LocalDate endDate = startDate.plus(period);
            endDate = (endDate.isAfter(finalEndDate)) ? finalEndDate : endDate;
            ranges.add(new SelectQueryRange(startDate, endDate.minus(Period.ofDays(1))));
            startDate = endDate;
        }

        return ranges;
    }

    /**
     * compares if period x is longer than period y
     * (stupid algorithm, it will fail for Y0M48D0 > Y1M0D0, because it does not convert month to years)
     * @param x
     * @param y
     * @return 1 if x is longer, 0 if equal, -1 if shorter
     */
    public int compare(Period x, Period y) {
        int xYears = x.getYears();
        int yYears = y.getYears();

        if (xYears != yYears) {return xYears > yYears ? 1 : -1;}

        int xMonth = x.getMonths();
        int yMonth = y.getMonths();

        if (xMonth != yMonth) {return xMonth > yMonth ? 1 : -1;}

        int xDays = x.getDays();
        int yDays = y.getDays();

        if (xDays != yDays) {return xDays > yDays ? 1 : -1;}

        return 0;
    }
}
