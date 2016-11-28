package de.steinberg.rpc.execution.trigger.core.messaging;

import de.steinberg.rpc.execution.trigger.core.exception.MessageQueueException;

import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by lkleen on 11/28/2016.
 */
public interface MessageQueue {

    <T> void send(Class<T> type, T message);

    <T> T receive(Class<T> type);

}
