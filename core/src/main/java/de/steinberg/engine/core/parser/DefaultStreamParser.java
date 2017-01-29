package de.steinberg.engine.core.parser;

import de.steinberg.engine.core.exception.ParserException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lars on 29.01.2017.
 */
public class DefaultStreamParser implements StreamParser {

    ObjectMapper mapper;

    @Override
    public <T> List<T> read(Class<T> type, InputStream input) {
        ArrayList<T> result = new ArrayList<T>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                result.add(mapper.read(type, line));
            }
        } catch (Exception e) {
            throw new ParserException(e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new ParserException(e);
            }
        }
        return result;
    }

    @Override
    public <T> void write(Class<T> type, List<T> data, OutputStream output) {

    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }
}
