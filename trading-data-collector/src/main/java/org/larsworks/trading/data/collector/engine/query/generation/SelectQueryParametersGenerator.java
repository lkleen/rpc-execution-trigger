package org.larsworks.trading.data.collector.engine.query.generation;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 28.01.2017.
 */
public interface SelectQueryParametersGenerator {

    List<SelectQueryParameters> generateParameters(String symbol, SelectQueryRange range, Period period);

    List<SelectQueryParameters> generateParameters(List<String> symbols, SelectQueryRange range, Period period);

    /**
     * splits the range of start and end day for start and end date to x ranges
     * with a size of period. If the range is split the last day of the input range is exclusive.
     * eg. a range fom 1990/01/01 to 1992/01/01 is split with a period of 1 year ti will include the ranges
     * 1990/01/01 to 1990/12/31
     * and
     * 1991/01/01 to 1991/12/31
     *
     * @param period the period to split the range to
     * @param range  the overall range (with the last day excluded)
     * @return
     */
    List<SelectQueryRange> splitRanges(Period period, SelectQueryRange range);

}
