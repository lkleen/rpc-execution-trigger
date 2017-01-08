package de.steinberg.engine.core.protocol.receiver;

import de.steinberg.engine.core.protocol.message.Message;

/**
 * Created by lkleen on 22.11.2016.
 */
public abstract class StringReceiver implements Receiver<String> {
    public Message<String> receive() {
        return receiveMessage();
    }

    protected abstract Message<String> receiveMessage();
}
