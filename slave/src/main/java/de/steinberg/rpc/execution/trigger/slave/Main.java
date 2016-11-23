package de.steinberg.rpc.execution.trigger.slave;

import de.steinberg.rpc.execution.trigger.core.protocol.StringMessage;
import de.steinberg.rpc.execution.trigger.core.protocol.impl.SocketStringSender;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by lkleen on 22.11.2016.
 */
@Slf4j
public class Main {

    public static void main (String... args)
    {
        log.info("sending request");

        SocketStringSender sender = new SocketStringSender();
        StringMessage msg = new StringMessage();
        msg.setValue("hello my master");
        sender.send(msg);

        log.info("request sent");

    }

}
