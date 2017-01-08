package de.steinberg.engine.core.exception;

/**
 * base exception class
 * Created by lkleen on 22.11.2016.
 */
public class EngineException extends RuntimeException {

    public EngineException(Throwable cause) {
        super(cause);
    }

    public EngineException(String message) {
        super(message);
    }

}
