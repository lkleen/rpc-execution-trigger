package de.steinberg.rpc.execution.trigger.core.configuration;

import de.steinberg.rpc.execution.trigger.core.messaging.BlockingMessageQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * core components bean definitions
 *
 * Created by lkleen on 11/28/2016.
 */
@Configuration
public class CoreConfiguration {

    @Bean
    public BlockingMessageQueue blockingMessageQueue() {
        return new BlockingMessageQueue();
    }

}
