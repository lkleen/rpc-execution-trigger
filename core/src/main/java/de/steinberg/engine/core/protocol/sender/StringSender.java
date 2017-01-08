package de.steinberg.engine.core.protocol.sender;

import de.steinberg.engine.core.protocol.message.Message;

/**
 * Created by lkleen on 22.11.2016.
 */
public abstract class StringSender implements Sender<String> {
    public void send(Message<String> message) {
        sendMessage(message);
    }

    protected abstract void sendMessage(Message<String> message);
}
