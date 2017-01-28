package org.larsworks.trading.data.collector.engine.query.generation;

import lombok.Data;

/**
 * @author Lars Kleen
 * @since ?version
 *        Date: 08.01.17
 *        Time: 17:59
 */
@Data
public class SelectQueryParameters {
    String symbol;
    SelectQueryRange range;
}
