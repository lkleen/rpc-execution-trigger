package de.steinberg.engine.ui;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.Layout;
import de.steinberg.engine.core.messaging.BlockingMessageQueue;

/**
 * Created by lkleen on 12/14/2016.
 */
public class MessageQueueAppender extends ConsoleAppender<ILoggingEvent> {

    static public BlockingMessageQueue messageQueue;

    Layout<ILoggingEvent> layout;

    private void initialize() {
        if (layout == null) {
            PatternLayoutEncoder encoder = (PatternLayoutEncoder) this.getEncoder();
            layout = encoder.getLayout();
        }
    }

    @Override
    protected void append(ILoggingEvent e) {
        initialize();
        if (messageQueue != null)
            messageQueue.send(layout.doLayout(e));
    }
}
