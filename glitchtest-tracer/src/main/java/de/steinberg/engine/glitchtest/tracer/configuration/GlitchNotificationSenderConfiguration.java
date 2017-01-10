package de.steinberg.engine.glitchtest.tracer.configuration;

import de.steinberg.engine.core.engine.monitor.FileAddedMonitor;
import de.steinberg.engine.core.protocol.sender.IntegerSocketSender;
import de.steinberg.engine.glitchtest.tracer.engine.GlitchNotificationAction;
import de.steinberg.engine.glitchtest.tracer.engine.GlitchNotificationSenderEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lkleen on 11/28/2016.
 */
@Configuration
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
