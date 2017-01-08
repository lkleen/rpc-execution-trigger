package de.steinberg.engine.core.messaging;

import java.util.concurrent.TimeUnit;

/**
 * sending and receiving application internal messages. eg. display text for the ui
 *
 * Created by lkleen on 11/28/2016.
 */
public interface MessageQueue {

    <T> void send(T message);

    <T> T receive(Class<T> type, long timeout, TimeUnit timeUnit);

}
