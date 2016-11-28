package de.steinberg.rpc.execution.trigger.master;

import de.steinberg.rpc.execution.trigger.core.protocol.Message;
import de.steinberg.rpc.execution.trigger.core.protocol.impl.SocketStringReceiver;
import de.steinberg.rpc.execution.trigger.master.network.NetworkInterfacesInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by lkleen on 22.11.2016.
 */
@Slf4j
public class Main {

    public static void main (String... args)
    {
        log.info("NETWORK INTERFACES");
        log.info(NetworkInterfacesInfo.create ());

        SocketStringReceiver receiver = new SocketStringReceiver();
        log.info("waiting for request");

        while (true) {
            Message<String> message = receiver.receive();
            log.info("received: {}", message.getValue());
        }
        //receiver.close();
    }

}
