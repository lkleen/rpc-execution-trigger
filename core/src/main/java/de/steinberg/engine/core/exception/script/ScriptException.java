package de.steinberg.engine.core.exception.script;

import de.steinberg.engine.core.exception.EngineException;

/**
 * Created by LKLeen on 12.01.2017.
 */
public class ScriptException extends EngineException {
    public ScriptException(String message) {
        super(message);
    }

    public ScriptException(Throwable cause) {
        super(cause);
    }
}
