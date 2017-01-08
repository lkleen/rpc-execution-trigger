package de.steinberg.rpc.execution.trigger.glitchnotificationsender.configuration;

import de.steinberg.rpc.execution.trigger.core.configuration.CoreConfiguration;
import de.steinberg.rpc.execution.trigger.core.engine.action.GlitchNotificationAction;
import de.steinberg.rpc.execution.trigger.core.engine.monitor.FileAddedMonitor;
import de.steinberg.rpc.execution.trigger.core.protocol.sender.IntegerSocketSender;
import de.steinberg.rpc.execution.trigger.glitchnotificationsender.engine.GlitchNotificationSenderEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.xml.ws.WebEndpoint;

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
