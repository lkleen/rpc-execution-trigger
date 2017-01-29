package de.steinberg.engine.core.parser;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by lars on 29.01.2017.
 */
public interface StreamParser {

    /**
     * reads from InputStream and returns a list of objects
     * @param type
     * @param input
     * @param <T>
     * @return
     */
    <T> List<T> read(Class<T> type, InputStream input);

    /**
     * writes a list of objects to an OutputStream
     * @param type
     * @param data
     * @param output
     * @param <T>
     */
    <T> void write(Class<T> type, List<T> data, OutputStream output);

}
