package de.steinberg.engine.core.process;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LKLeen on 12.01.2017.
 */
public class ErrorStreamParser {

    public static interface Validator {
        boolean isError(String text);
    }

    List<Validator> validators = new ArrayList<>();
    List<String> errors = new ArrayList<>();

    public void addValidator(Validator validator) {
        validators.add(validator);
    }

    public void parseLine(String line) {
        if (isError(line)) {
            errors.add(line);
        }
    }

    protected boolean isError(String line) {
        for (Validator validator : validators) {
            if (validator.isError(line))
                return true;
        }
        return false;
    }

    public void clearErrors() {errors.clear();}

    public List<String> getErrors() {
        return errors;
    }

}
