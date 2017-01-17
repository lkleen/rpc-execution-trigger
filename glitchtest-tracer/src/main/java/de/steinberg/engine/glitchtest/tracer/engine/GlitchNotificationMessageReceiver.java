package de.steinberg.engine.glitchtest.tracer.engine;

import de.steinberg.engine.core.exception.SocketReceiverException;
import de.steinberg.engine.core.protocol.message.GlitchNotificationMessage;
import de.steinberg.engine.core.protocol.message.Message;
import de.steinberg.engine.core.protocol.receiver.SingleCharSocketReceiver;

/**
 * Created by LKLeen on 29.12.2016.
 */
public class GlitchNotificationMessageReceiver extends SingleCharSocketReceiver {
    @Override
    protected Message<Character> createMessage(char value) {
        return new GlitchNotificationMessage();
    }
}
