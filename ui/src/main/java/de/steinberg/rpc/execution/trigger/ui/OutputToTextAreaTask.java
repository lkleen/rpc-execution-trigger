package de.steinberg.rpc.execution.trigger.ui;

import ch.qos.logback.classic.spi.LoggingEvent;
import de.steinberg.rpc.execution.trigger.core.messaging.BlockingMessageQueue;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
import lombok.Setter;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

/**
 * Created by lkleen on 12/14/2016.
 */
public class OutputToTextAreaTask extends Task<Void> {

    @Inject
    BlockingMessageQueue messageQueue;

    @Setter
    TextArea output;

    @Override
    protected Void call() throws Exception {
        StringBuilder builder = new StringBuilder(1024);
        while (true) {
            String message = messageQueue.receive(String.class, 100, TimeUnit.MILLISECONDS);
            while (message != null) {
                builder.append(message);
                message = messageQueue.receive(String.class, 0, TimeUnit.MILLISECONDS);
            }
            if (builder.length() > 0) {
                output.appendText(builder.toString());
                builder.delete(0, builder.length());
            }
            Thread.sleep(100);
        }
    }
}
