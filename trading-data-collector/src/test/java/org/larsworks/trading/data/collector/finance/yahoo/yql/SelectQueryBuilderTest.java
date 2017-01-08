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
        SelectQueryParameters parmeters = null;
        builder.validateParameters(parmeters);
    }

}
