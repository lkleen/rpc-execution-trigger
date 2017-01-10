package de.steinberg.engine.core.protocol.message;

/**
 * Created by LKLeen on 29.12.2016.
 */
public class GlitchNotificationMessage implements Message<Character> {

    public static final char ID = (char) "GlitchNotificationMessage".hashCode();

    @Override
    public Character getValue() {
        return ID;
    }

}
