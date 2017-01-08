package de.steinberg.engine.core.protocol.message;

/**
 * Created by LKLeen on 29.12.2016.
 */
public class GlitchNotificationMessage implements Message<Integer> {

    public static final int ID = "GlitchNotificationMessage".hashCode();

    @Override
    public Integer getValue() {
        return ID;
    }

}
