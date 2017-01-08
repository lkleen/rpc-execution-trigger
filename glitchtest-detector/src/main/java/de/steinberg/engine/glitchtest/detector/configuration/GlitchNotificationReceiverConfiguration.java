package de.steinberg.engine.glitchtest.detector.configuration;

import de.steinberg.engine.core.engine.action.WriteTraceAction;
import de.steinberg.engine.core.engine.monitor.GlitchNotificationMonitor;
import de.steinberg.engine.core.protocol.receiver.GlitchNotificationMessageReceiver;
import de.steinberg.engine.glitchtest.detector.engine.GlitchNotificationReceiverEngine;
import org.springframework.context.annotation.Bean;

/**
 * Created by lkleen on 11/28/2016.
 */
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
