package de.steinberg.rpc.execution.trigger.glitchnotificationsender.configuration;

import de.steinberg.engine.core.configuration.CoreConfiguration;
import de.steinberg.engine.core.engine.action.GlitchNotificationAction;
import de.steinberg.engine.core.engine.monitor.FileAddedMonitor;
import de.steinberg.engine.core.protocol.sender.IntegerSocketSender;
import de.steinberg.rpc.execution.trigger.glitchnotificationsender.engine.GlitchNotificationSenderEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by lkleen on 11/28/2016.
 */
@Configuration
@Import(CoreConfiguration.class)
public class GlitchNotificationSenderConfiguration {

    @Bean
    public GlitchNotificationSenderEngine glitchNotificationSenderEngine() {
        GlitchNotificationSenderEngine glitchNotificationSenderEngine = new GlitchNotificationSenderEngine();
        glitchNotificationSenderEngine.addMonitor(fileAddedMonitor());
        return glitchNotificationSenderEngine;
    }

    @Bean
    public FileAddedMonitor fileAddedMonitor() {
        FileAddedMonitor fileAddedMonitor = new FileAddedMonitor();
        fileAddedMonitor.addAction(glitchNotificationAction());
        return fileAddedMonitor;
    }

    @Bean
    public IntegerSocketSender integerSocketSender() {
        return new IntegerSocketSender();
    }

    @Bean
    public GlitchNotificationAction glitchNotificationAction() {
        return new GlitchNotificationAction();
    }

}
