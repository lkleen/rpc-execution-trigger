package de.steinberg.engine.core.exception;

/**
 * Created by lars on 29.01.2017.
 */
public class ParserException extends EngineException {
    public ParserException(String message) {
        super(message);
    }

    public ParserException(Throwable cause) {
        super(cause);
    }
}
