package de.steinberg.rpc.execution.trigger.master;

import de.steinberg.rpc.execution.trigger.core.protocol.Message;
import de.steinberg.rpc.execution.trigger.core.protocol.impl.SocketStringReceiver;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by lkleen on 22.11.2016.
 */
@Slf4j
public class Main {

    public static void main (String... args)
    {
        SocketStringReceiver receiver = new SocketStringReceiver();
        log.info("waiting for request");

        try {
            while (true) {
                Message<String> message = receiver.receive();
                log.info("received: {}", message.getValue());
            }
        } finally {
            receiver.close();
        }
    }

}
