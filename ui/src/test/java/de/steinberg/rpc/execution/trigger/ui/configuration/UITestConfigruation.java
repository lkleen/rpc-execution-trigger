package de.steinberg.rpc.execution.trigger.ui.configuration;

import de.steinberg.engine.core.engine.*;
import de.steinberg.engine.core.engine.monitor.Monitor;
import de.steinberg.rpc.execution.trigger.ui.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lkleen on 11/29/2016.
 */
@Configuration
public class UITestConfigruation extends UIConfiguration {

    @Bean
    public Engine engine() {

        Monitor monitor1 = new MonitorA();
        monitor1.addAction(new ActionA());
        monitor1.addAction(new ActionB());

        Monitor monitor2 = new MonitorB();
        monitor2.addAction(new ActionA());

        Monitor monitor3 = new MonitorC();

        DefaultEngine engine = new DefaultEngine();
        engine.addMonitor(monitor1);
        engine.addMonitor(monitor2);
        engine.addMonitor(monitor3);
        return engine;
    }

}
