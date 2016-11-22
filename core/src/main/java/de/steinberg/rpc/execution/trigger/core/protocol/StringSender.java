package de.steinberg.rpc.execution.trigger.core.protocol;

/**
 * Created by lkleen on 22.11.2016.
 */
public abstract class StringSender implements Sender<String> {
    public void send(Message<String> message) {
        sendMessage(message);
    }

    protected abstract void sendMessage(Message<String> message);
}
