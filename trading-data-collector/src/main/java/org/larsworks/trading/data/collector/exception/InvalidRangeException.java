package org.larsworks.trading.data.collector.exception;

import de.steinberg.engine.core.exception.EngineException;

/**
 * @author Lars Kleen
 * @since ?version
 *        Date: 08.01.17
 *        Time: 19:00
 */
public class InvalidRangeException extends EngineException {
    public InvalidRangeException(String message) {
        super(message);
    }
}
