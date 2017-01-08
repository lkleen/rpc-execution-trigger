package de.steinberg.engine.core.configuration;

import de.steinberg.engine.core.annotations.DisplayNameResolver;
import de.steinberg.engine.core.messaging.BlockingMessageQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * core components bean definitions
 *
 * Created by lkleen on 11/28/2016.
 */
@Configuration
public class CoreConfiguration {

    @Bean
    public DisplayNameResolver displayNameResolver() {
        return new DisplayNameResolver();
    }

    @Bean
    public BlockingMessageQueue blockingMessageQueue() {
        return new BlockingMessageQueue();
    }

    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        return Executors.newSingleThreadScheduledExecutor();
    }

}
