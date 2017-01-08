package org.larsworks.trading.data.collector.exception;

import de.steinberg.engine.core.exception.EngineException;

/**
 * @author Lars Kleen
 * @since ?version
 *        Date: 08.01.17
 *        Time: 17:56
 */
public class QueryBuilderException extends EngineException {
    public QueryBuilderException(String message) {
        super(message);
    }
}
