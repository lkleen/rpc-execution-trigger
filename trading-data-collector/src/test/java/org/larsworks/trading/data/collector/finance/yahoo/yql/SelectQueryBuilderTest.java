package org.larsworks.trading.data.collector.finance.yahoo.yql;

import org.larsworks.trading.data.collector.exception.QueryBuilderException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;

/**
 * @author Lars Kleen
 * @since ?version
 *        Date: 08.01.17
 *        Time: 18:32
 */
public class SelectQueryBuilderTest {

    SelectQueryBuilder builder = new SelectQueryBuilder();
    SelectQueryRange validRange = new SelectQueryRange(
        LocalDate.of(2014, 1, 1),
        LocalDate.of(2014, 6, 1)
    );

    @Test(expectedExceptions = QueryBuilderException.class)
    public void testQueryParametersNull() {
        SelectQueryParameters parameters = null;
        builder.validateParameters(parameters);
    }

    @Test(expectedExceptions = QueryBuilderException.class)
    public void testQueryParametersRangeNull() {
        SelectQueryParameters parameters = new SelectQueryParameters();
        parameters.symbol = "ASD";
        builder.validateParameters(parameters);
    }

    @Test(expectedExceptions = QueryBuilderException.class)
    public void testQueryParametersSymbolNull() {
        SelectQueryParameters parameters = new SelectQueryParameters();
        parameters.range = validRange;
        builder.validateParameters(parameters);
    }

    @Test(expectedExceptions = QueryBuilderException.class)
    public void testQueryParametersSymbolEmpty() {
        SelectQueryParameters parameters = new SelectQueryParameters();
        parameters.range = validRange;
        parameters.symbol = "";
        builder.validateParameters(parameters);
    }

    @Test(expectedExceptions = QueryBuilderException.class)
    public void testQueryParametersZeroRange() {
        SelectQueryParameters parameters = new SelectQueryParameters();
        LocalDate startDate = LocalDate.of(2016, 1, 1);
        LocalDate endDate = LocalDate.of(2016, 1, 1);
        parameters.range = new SelectQueryRange(startDate, endDate);
        parameters.symbol = "FOO";
        builder.validateParameters(parameters);
    }

    @Test(expectedExceptions = QueryBuilderException.class)
    public void testQueryParametersNegativeRange() {
        SelectQueryParameters parameters = new SelectQueryParameters();
        LocalDate startDate = LocalDate.of(2016, 1, 1);
        LocalDate endDate = LocalDate.of(2015, 12, 31);
        parameters.range = new SelectQueryRange(startDate, endDate);
        parameters.symbol = "FOO";
        builder.validateParameters(parameters);
    }

    @Test
    public void testQueryParametersValid() {
        SelectQueryParameters parameters = new SelectQueryParameters();
        LocalDate startDate = LocalDate.of(2016, 1, 1);
        LocalDate endDate = LocalDate.of(2016, 1, 2);
        parameters.range = new SelectQueryRange(startDate, endDate);
        parameters.symbol = "FOO";
        builder.validateParameters(parameters);
    }

    @Test
    public void testBuildQuery() {
        final String expected = "select * from yahoo.finance.historicaldata where symbol = \"YHOO\" and startDate = \"2015-01-01\" and endDate = \"2016-01-01\"";
        SelectQueryParameters parameters = new SelectQueryParameters();
        parameters.symbol = "YHOO";
        parameters.range = new SelectQueryRange(
                LocalDate.of(2015,1,1),
                LocalDate.of(2016,1,1)
        );
        final String actual = builder.build(parameters).getString();
        Assert.assertEquals(actual, expected);
    }



}
