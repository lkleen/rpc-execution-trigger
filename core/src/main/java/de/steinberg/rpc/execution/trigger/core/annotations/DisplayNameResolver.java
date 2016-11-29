package de.steinberg.rpc.execution.trigger.core.annotations;

import java.lang.annotation.Annotation;

/**
 * Created by lkleen on 11/29/2016.
 */
public class DisplayNameResolver {

    public String resolveFrom(Object obj) {
        Class<?> clazz = obj.getClass();
        Annotation[] anno = clazz.getAnnotations();

        boolean check = clazz.isAnnotationPresent(DisplayName.class);

        DisplayName displayName = obj.getClass().getAnnotation(DisplayName.class);
        if (displayName != null) {
            return displayName.value();
        } else {
            return obj.getClass().getSimpleName();
        }


    }


}
