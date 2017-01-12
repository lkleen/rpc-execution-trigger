package de.steinberg.engine.core.exception.script;

import de.steinberg.engine.core.exception.script.ScriptException;

import java.util.List;

/**
 * Created by LKLeen on 12.01.2017.
 */
public class ErrorStreamParserException extends ScriptException {

    public final List<String> errors;

    public ErrorStreamParserException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }
}
