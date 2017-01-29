package de.steinberg.engine.core.parser;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by lars on 29.01.2017.
 */
public interface ObjectMapper {

    <T> T read(Class<T> type, String input);

    <T> String write(Class<T> type, T data);

}
