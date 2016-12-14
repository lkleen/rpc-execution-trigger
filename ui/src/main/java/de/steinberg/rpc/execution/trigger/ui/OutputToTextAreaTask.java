package de.steinberg.rpc.execution.trigger.ui;

import ch.qos.logback.classic.spi.LoggingEvent;
import de.steinberg.rpc.execution.trigger.core.messaging.BlockingMessageQueue;
import javafx.concurrent.Task;

import javax.inject.Inject;

/**
 * Created by lkleen on 12/14/2016.
 */
public class OutputToTextAreaTask extends Task<Void> {

    @Inject
    BlockingMessageQueue messageQueue;

    @Override
    protected Void call() throws Exception {
        while (true) {
            LoggingEvent event = messageQueue.receive(LoggingEvent.class);
            System.out.println("received " + event);
        }
    }
}
