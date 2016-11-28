package de.steinberg.rpc.execution.trigger.slave;

import de.steinberg.rpc.execution.trigger.core.protocol.StringMessage;
import de.steinberg.rpc.execution.trigger.core.protocol.impl.SocketStringSender;
import de.steinberg.rpc.execution.trigger.slave.configuration.SlaveConfiguration;
import de.steinberg.rpc.execution.trigger.slave.engine.SlaveEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by lkleen on 22.11.2016.
 */
@Slf4j
public class Main  {

    public static void main (String... args) throws Exception
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(SlaveConfiguration.class);
        UI ui = context.getBean(UI.class);
        ui.run();


        Thread.sleep(60 * 1000);
        /*

        SocketStringSender sender = new SocketStringSender();
        sender.setHost("10.129.74.73");

        StringMessage msg = new StringMessage();
        msg.setValue("hello my master");
        sender.send(msg);

        log.info("request sent");

        */
    }

}
