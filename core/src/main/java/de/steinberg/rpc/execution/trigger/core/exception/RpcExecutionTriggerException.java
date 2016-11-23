package de.steinberg.rpc.execution.trigger.core.exception;

/**
 * base exception class
 * Created by lkleen on 22.11.2016.
 */
public class RpcExecutionTriggerException extends RuntimeException {

    public RpcExecutionTriggerException(Throwable cause) {
        super(cause);
    }

    protected RpcExecutionTriggerException(String message) {
        super(message);
    }
}
