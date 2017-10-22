package org.larsworks.trading.data.collector.exception;

import de.steinberg.engine.core.exception.EngineException;

/**
 * thrown when something went wrong when loading/storing stuff
 */
public class PersistenceException extends EngineException {
    public PersistenceException(Throwable cause) {
        super(cause);
    }
}
