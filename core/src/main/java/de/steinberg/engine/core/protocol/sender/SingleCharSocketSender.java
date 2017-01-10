package de.steinberg.engine.core.protocol.sender;

import de.steinberg.engine.core.protocol.message.Message;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by LKLeen on 29.12.2016.
 */
public class SingleCharSocketSender extends SocketSender<Character> {
    @Override
    protected void sendMessage(BufferedWriter writer, Message<Character> message) throws IOException {
        writer.write(message.getValue());
    }
}
