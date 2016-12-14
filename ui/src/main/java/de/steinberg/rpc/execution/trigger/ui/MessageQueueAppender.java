package de.steinberg.rpc.execution.trigger.ui;

import ch.qos.logback.core.AppenderBase;
import de.steinberg.rpc.execution.trigger.core.messaging.BlockingMessageQueue;

/**
 * Created by lkleen on 12/14/2016.
 */
public class MessageQueueAppender<E> extends AppenderBase<E> {

    static public BlockingMessageQueue messageQueue;

    @Override
    protected void append(E e) {
        if (messageQueue != null)
            messageQueue.send(e);

        System.out.println(e.toString());
    }
}
