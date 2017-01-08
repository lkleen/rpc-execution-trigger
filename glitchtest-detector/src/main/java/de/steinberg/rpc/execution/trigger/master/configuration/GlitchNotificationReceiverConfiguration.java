package de.steinberg.rpc.execution.trigger.master.configuration;

import de.steinberg.rpc.execution.trigger.core.engine.DefaultEngine;
import de.steinberg.rpc.execution.trigger.core.engine.Engine;
import de.steinberg.rpc.execution.trigger.core.engine.action.WriteTraceAction;
import de.steinberg.rpc.execution.trigger.core.engine.monitor.Monitor;
import de.steinberg.rpc.execution.trigger.core.engine.monitor.GlitchNotificationMonitor;
import de.steinberg.rpc.execution.trigger.core.protocol.receiver.GlitchNotificationMessageReceiver;
import de.steinberg.rpc.execution.trigger.master.engine.GlitchNotificationReceiverEngine;
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
