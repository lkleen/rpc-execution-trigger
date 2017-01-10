package de.steinberg.engine.glitchtest.detector.configuration;

import de.steinberg.engine.core.engine.monitor.FileAddedMonitor;
import de.steinberg.engine.core.protocol.sender.SingleCharSocketSender;
import de.steinberg.engine.glitchtest.detector.engine.GlitchNotificationAction;
import de.steinberg.engine.glitchtest.detector.engine.GlitchNotificationSenderEngine;
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
    public SingleCharSocketSender integerSocketSender() {
        return new SingleCharSocketSender();
    }

    @Bean
    public GlitchNotificationAction glitchNotificationAction() {
        return new GlitchNotificationAction();
    }

}
