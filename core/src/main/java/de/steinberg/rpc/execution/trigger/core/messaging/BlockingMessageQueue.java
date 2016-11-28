package de.steinberg.rpc.execution.trigger.core.messaging;

import de.steinberg.rpc.execution.trigger.core.exception.MessageQueueException;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by lkleen on 11/28/2016.
 */
public class BlockingMessageQueue implements MessageQueue {

    final int secondsTimeout = 1;

    boolean shutdown = false;

    HashMap<Class<?>, Queue<?>> map = new HashMap<Class<?>, Queue<?>>();

    public <T> void send(Class<T> type, T message) {
        LinkedBlockingQueue<T> queue = (LinkedBlockingQueue<T>) map.get(type);
        if (queue == null)
        {
            queue = new LinkedBlockingQueue<T>();
            map.put(type, queue);
        }
        queue.offer(message);
    }

    public <T> T receive(Class<T> type) {
        try {
            LinkedBlockingQueue<T> queue = (LinkedBlockingQueue<T>) map.get(type);
            while (queue == null && !shutdown) {
                Thread.sleep(1000);
                queue = (LinkedBlockingQueue<T>) map.get(type);
            }
            T message = null;
            while (message == null && !shutdown) {
                message = queue.poll(secondsTimeout, TimeUnit.SECONDS);
            }
            return message;
        } catch (InterruptedException e) {
            throw new MessageQueueException(e);
        }
    }

    public void shutdown() {
        shutdown = true;
    }

}
