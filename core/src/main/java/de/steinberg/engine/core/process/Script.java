package de.steinberg.engine.core.process;

/**
 * Created by LKLeen on 12.01.2017.
 */
public class Script {

    public final String filename;
    public final int numParameters;

    public Script(String filename, int numParameters) {
        this.filename = filename;
        this.numParameters = numParameters;
    }
}
