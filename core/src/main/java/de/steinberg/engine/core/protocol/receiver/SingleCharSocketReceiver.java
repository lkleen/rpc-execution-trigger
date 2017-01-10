package de.steinberg.engine.core.protocol.receiver;

import de.steinberg.engine.core.protocol.message.Message;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by LKLeen on 29.12.2016.
 */
public abstract class SingleCharSocketReceiver extends SocketReceiver<Character> {

    @Override
    protected Message<Character> receiveMessage(InputStreamReader reader) throws IOException {
        char value = (char) reader.read();
        return createMessage(value);
    }

    protected abstract Message<Character> createMessage(char value);
}
