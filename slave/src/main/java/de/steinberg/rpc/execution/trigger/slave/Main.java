package de.steinberg.rpc.execution.trigger.slave;

import de.steinberg.rpc.execution.trigger.core.protocol.StringMessage;
import de.steinberg.rpc.execution.trigger.core.protocol.impl.SocketStringSender;

/**
 * Created by lkleen on 22.11.2016.
 */
public class Main {

    public static void main (String... args)
    {
        System.out.println("SLAVE sending request");

        SocketStringSender sender = new SocketStringSender();
        StringMessage msg = new StringMessage();
        msg.setValue("hello my master");
        sender.send(msg);

        System.out.println("SLAVE sent request");

    }

}
