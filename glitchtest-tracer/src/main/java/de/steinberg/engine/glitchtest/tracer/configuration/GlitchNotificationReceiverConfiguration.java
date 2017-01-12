package de.steinberg.engine.glitchtest.tracer.configuration;

import de.steinberg.engine.core.engine.action.TracerRecorder;
import de.steinberg.engine.core.process.ScriptRunner;
import de.steinberg.engine.glitchtest.tracer.engine.GlitchNotificationMessageReceiver;
import de.steinberg.engine.glitchtest.tracer.engine.GlitchNotificationMonitor;
import de.steinberg.engine.glitchtest.tracer.engine.GlitchNotificationReceiverEngine;
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
    public TracerRecorder writeTraceAction() {
        return new TracerRecorder();
    }

    @Bean
    public ScriptRunner scriptRunner() {return new ScriptRunner();}

}
