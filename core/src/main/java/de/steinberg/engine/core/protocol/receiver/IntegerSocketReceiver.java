package de.steinberg.engine.core.protocol.receiver;

import de.steinberg.engine.core.protocol.message.Message;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by LKLeen on 29.12.2016.
 */
public abstract class IntegerSocketReceiver extends SocketReceiver<Integer> {

    @Override
    protected Message<Integer> receiveMessage(InputStreamReader reader) throws IOException {
        int value = reader.read();
        return createMessage(value);
    }

    protected abstract Message<Integer> createMessage(int value);
}
