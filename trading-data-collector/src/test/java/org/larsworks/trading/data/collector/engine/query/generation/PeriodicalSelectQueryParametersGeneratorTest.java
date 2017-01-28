package org.larsworks.trading.data.collector.engine.query.generation;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lars on 28.01.2017.
 */
public class PeriodicalSelectQueryParametersGeneratorTest {

    final PeriodicalSelectQueryParametersGenerator generator = new PeriodicalSelectQueryParametersGenerator();

    @Test
    public void testSplitLessThanAYear() {
        Period period = Period.ofMonths(12);
        SelectQueryRange range = new SelectQueryRange(
                LocalDate.of(1981, 1, 1), LocalDate.of(1981, 3, 1)
        );
        List<SelectQueryRange> ranges = generator.splitRanges(period, range);
        Assert.assertEquals(ranges.size(), 1);
    }

    @Test
    public void testSplitExactlyAYear() {
        Period period = Period.ofMonths(12);
        SelectQueryRange range = new SelectQueryRange(
                LocalDate.of(1981, 1, 1), LocalDate.of(1982, 1, 1)
        );
        List<SelectQueryRange> ranges = generator.splitRanges(period, range);
        Assert.assertEquals(ranges.size(), 1);
    }

    @Test
    public void testSplitYearPlus1Day() {
        Period period = Period.ofYears(1);
        SelectQueryRange range = new SelectQueryRange(
                LocalDate.of(1981, 1, 1), LocalDate.of(1982, 1, 2)
        );
        List<SelectQueryRange> ranges = generator.splitRanges(period, range);
        Assert.assertEquals(ranges.size(), 2);
    }

    @Test
    public void testSplitMultipleYears() {
        Period period = Period.ofYears(1);
        SelectQueryRange range = new SelectQueryRange(
                LocalDate.of(1981, 1, 1), LocalDate.of(1983, 1, 3) // until 1983/01/2
        );
        List<SelectQueryRange> ranges = generator.splitRanges(period, range);
        Assert.assertEquals(ranges.size(), 3);

        SelectQueryRange year1981 = ranges.get(0); // 365 days
        SelectQueryRange year1982 = ranges.get(1); // 365 days
        SelectQueryRange year1983 = ranges.get(2); //   1 day

        long numDays = ChronoUnit.DAYS.between(year1981.getStartDate(), year1981.getEndDate());
        Assert.assertEquals(numDays, 364);
        numDays = ChronoUnit.DAYS.between(year1982.getStartDate(), year1982.getEndDate());
        Assert.assertEquals(numDays, 364);
        numDays = ChronoUnit.DAYS.between(year1983.getStartDate(), year1983.getEndDate());
        Assert.assertEquals(numDays, 1);
    }

    @Test
    public void testGenerateQueryParameters() {
        List<String> symbols = new ArrayList<>();
        symbols.add("FOO");
        symbols.add("BAR");
        generator.setSymbols(symbols);
        generator.setRange(new SelectQueryRange(
                LocalDate.of(2000, 1, 1),
                LocalDate.of(2005, 1, 1)
                ));
        generator.setPeriod(Period.ofYears(1));

        List<SelectQueryParameters> parameters = generator.generateParameters(
                symbols,
                new SelectQueryRange(
                        LocalDate.of(2000, 1, 1),
                        LocalDate.of(2005, 1, 1)
                ),
                Period.ofYears(1));

        Assert.assertEquals(parameters.size(), 2 * 5);

    }

}
