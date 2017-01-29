package de.steinberg.engine.core.parser.csv;

import de.steinberg.engine.core.exception.ParserException;
import de.steinberg.engine.core.parser.ObjectMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by lars on 29.01.2017.
 */
public class CsvObjectMapper implements ObjectMapper {

    private final Tokenizer tokenizer = new Tokenizer();

    /**
     * read from a single line and parses it to an object of type
     * @param type the type must provide the order of fields as represented in the csv data
     * @param line
     * @param <T>
     * @return
     */
    @Override
    public <T> T read(Class<T> type, String line) {
        List<String> tokens = tokenizer.tokenize(line);
        T object;
        try {
            object = type.newInstance();
            Field[] fields = type.getDeclaredFields();
            for (int i = 0; i < tokens.size(); i++) {
                Field field = fields[i];
                Method setter = type.getMethod(getSetterName(field.getName()), field.getType());
                setter.invoke(object, tokens.get(i));
            }
        } catch (Exception e) {
            throw new ParserException(e);
        }
        return object;
    }

    private String getSetterName(String name) {
        return "set" + name.substring(0,1).toUpperCase() + name.substring(1);
    }

    @Override
    public <T> String write(Class<T> type, T data) {
        return null;
    }


}
