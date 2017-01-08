package org.larsworks.trading.data.collector.finance.yahoo.yql;

import org.larsworks.trading.data.collector.exception.QueryBuilderException;
import org.testng.annotations.Test;

/**
 * @author Lars Kleen
 * @since ?version
 *        Date: 08.01.17
 *        Time: 18:32
 */
public class SelectQueryBuilderTest {

    SelectQueryBuilder builder = new SelectQueryBuilder();

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
        parameters.range = new SelectQueryRange();
        builder.validateParameters(parameters);
    }

    @Test(expectedExceptions = QueryBuilderException.class)
    public void testQueryParametersSymbolEmpty() {
        SelectQueryParameters parameters = new SelectQueryParameters();
        parameters.range = new SelectQueryRange();
        parameters.symbol = "";
        builder.validateParameters(parameters);
    }

    @Test(expectedExceptions = QueryBuilderException.class)
    public void testQueryParametersInvalidRange() {
        SelectQueryParameters parameters = new SelectQueryParameters();
        parameters.range = new SelectQueryRange();
        parameters.symbol = "FOO";
        builder.validateParameters(parameters);
    }

}
