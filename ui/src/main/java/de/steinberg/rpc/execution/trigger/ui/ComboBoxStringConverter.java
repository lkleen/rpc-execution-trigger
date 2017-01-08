package de.steinberg.rpc.execution.trigger.ui;

import de.steinberg.engine.core.annotations.DisplayNameResolver;
import javafx.util.StringConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * resolves a proper display name and returns the selected object accordingly
 *
 * Created by lkleen on 11/29/2016.
 */
public class ComboBoxStringConverter<T> extends StringConverter<T> {

    DisplayNameResolver displayNameResolver = new DisplayNameResolver();

    Map<String, T> map = new HashMap<String, T>();

    @Override
    public String toString(T object) {
        String key = displayNameResolver.resolveFrom(object);
        map.put(key, object);
        return key;
    }

    @Override
    public T fromString(String string) {
        return map.get(string);
    }
}
