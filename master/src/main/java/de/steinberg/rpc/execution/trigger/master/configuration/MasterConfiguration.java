package de.steinberg.rpc.execution.trigger.master.configuration;

import de.steinberg.rpc.execution.trigger.core.engine.DefaultEngine;
import de.steinberg.rpc.execution.trigger.core.engine.Engine;
import de.steinberg.rpc.execution.trigger.core.engine.monitor.Monitor;
import de.steinberg.rpc.execution.trigger.core.engine.monitor.GlitchMonitor;
import org.springframework.context.annotation.Bean;

/**
 * Created by lkleen on 11/28/2016.
 */
public class MasterConfiguration {

    @Bean
    public Engine engine() {
        Engine engine = new DefaultEngine();
        Monitor monitor = new GlitchMonitor();
        engine.addMonitor(monitor);
        return engine;
    }

}
