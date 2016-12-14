package de.steinberg.rpc.execution.trigger.core.messaging;

import de.steinberg.rpc.execution.trigger.core.exception.MessageQueueException;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * queue with a receive method which blocks until it receives something
 *
 * Created by lkleen on 11/28/2016.
 */
public class BlockingMessageQueue implements MessageQueue {

    boolean shutdown = false;

    HashMap<Class<?>, Queue<?>> map = new HashMap<Class<?>, Queue<?>>();

    public <T> void send(T message) {
        Class<?> type = message.getClass();
        LinkedBlockingQueue<T> queue = (LinkedBlockingQueue<T>) map.get(type);
        if (queue == null)
        {
            queue = new LinkedBlockingQueue<T>();
            map.put(type, queue);
        }
        queue.offer(message);
    }

    public <T> T receive(Class<T> type, long timeout, TimeUnit timeUnit) {
        try {
            LinkedBlockingQueue<T> queue = (LinkedBlockingQueue<T>) map.get(type);

            if (queue == null && !shutdown) {
                Thread.sleep(timeUnit.toMillis(timeout));
                queue = (LinkedBlockingQueue<T>) map.get(type);
                return queue == null ? null : queue.poll(0, TimeUnit.SECONDS);
            }

            return queue.poll(timeout, timeUnit);
        } catch (InterruptedException e) {
            throw new MessageQueueException(e);
        }
    }

    public void shutdown() {
        shutdown = true;
    }

}
