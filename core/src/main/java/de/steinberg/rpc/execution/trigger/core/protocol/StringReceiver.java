package de.steinberg.rpc.execution.trigger.core.protocol;

/**
 * Created by lkleen on 22.11.2016.
 */
public abstract class StringReceiver implements Receiver<String> {
    public Message<String> receive() {
        return receiveMessage();
    }

    protected abstract Message<String> receiveMessage();
}
