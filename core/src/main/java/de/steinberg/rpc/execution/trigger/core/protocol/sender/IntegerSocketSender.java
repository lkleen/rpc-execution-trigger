package de.steinberg.rpc.execution.trigger.core.protocol.sender;

import de.steinberg.rpc.execution.trigger.core.protocol.message.Message;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by LKLeen on 29.12.2016.
 */
public class IntegerSocketSender extends SocketSender<Integer> {
    @Override
    protected void sendMessage(BufferedWriter writer, Message<Integer> message) throws IOException {
        writer.write(message.getValue());
    }
}
