package de.steinberg.rpc.execution.trigger.core.protocol.receiver;

import de.steinberg.rpc.execution.trigger.core.exception.SocketReceiverException;
import de.steinberg.rpc.execution.trigger.core.protocol.message.GlitchNotificationMessage;
import de.steinberg.rpc.execution.trigger.core.protocol.message.Message;

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
