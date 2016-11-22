package de.steinberg.rpc.execution.trigger.master;

import de.steinberg.rpc.execution.trigger.core.protocol.Message;
import de.steinberg.rpc.execution.trigger.core.protocol.impl.SocketStringReceiver;

/**
 * Created by lkleen on 22.11.2016.
 */
public class Main {

    public static void main (String... args)
    {
        System.out.println("MASTER waiting for request");

        SocketStringReceiver receiver = new SocketStringReceiver();
        Message<String> message = receiver.receive();
        receiver.close();

        System.out.println("MASTER received: " + message.getValue());
    }

}
