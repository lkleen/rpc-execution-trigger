package de.steinberg.engine.glitchtest.detector.configuration;

import de.steinberg.engine.core.engine.action.WriteTraceAction;
import de.steinberg.engine.glitchtest.detector.engine.GlitchNotificationMessageReceiver;
import de.steinberg.engine.glitchtest.detector.engine.GlitchNotificationMonitor;
import de.steinberg.engine.glitchtest.detector.engine.GlitchNotificationReceiverEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lkleen on 11/28/2016.
 */
@Configuration
public class GlitchNotificationReceiverConfiguration {

    @Bean
    public GlitchNotificationReceiverEngine glitchNotificationReceiverEngine() {
        GlitchNotificationReceiverEngine engine = new GlitchNotificationReceiverEngine();
        engine.addMonitor(glitchNotificationMonitor());
        return engine;
    }

    @Bean
    public GlitchNotificationMonitor glitchNotificationMonitor() {
        GlitchNotificationMonitor monitor = new GlitchNotificationMonitor();
        monitor.addAction(writeTraceAction());
        return monitor;
    }

    @Bean
    public GlitchNotificationMessageReceiver glitchNotificationMessageReceiver() {
        return new GlitchNotificationMessageReceiver();
    }

    @Bean
    public WriteTraceAction writeTraceAction() {
        return new WriteTraceAction();
    }

}
