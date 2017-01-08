package de.steinberg.engine.core.protocol.sender;

import de.steinberg.engine.core.protocol.message.Message;

/**
 * Created by lkleen on 22.11.2016.
 */
public interface Sender<T> {
    void send(Message<T> value);
}
