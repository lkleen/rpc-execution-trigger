package de.steinberg.engine.core.protocol.receiver;

import de.steinberg.engine.core.exception.SocketReceiverException;
import de.steinberg.engine.core.protocol.message.GlitchNotificationMessage;
import de.steinberg.engine.core.protocol.message.Message;

/**
 * Created by LKLeen on 29.12.2016.
 */
public class GlitchNotificationMessageReceiver extends IntegerSocketReceiver {
    @Override
    protected Message<Integer> createMessage(int value) {
        if (value == GlitchNotificationMessage.ID) {
            return new GlitchNotificationMessage();
        } else {
            throw new SocketReceiverException("deserialization for value " + value + " not implemented");
        }
    }
}
