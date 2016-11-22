package de.steinberg.rpc.execution.trigger.core.protocol;

/**
 * Created by lkleen on 22.11.2016.
 */
public interface Receiver<T> {
    Message<T> receive();
}
