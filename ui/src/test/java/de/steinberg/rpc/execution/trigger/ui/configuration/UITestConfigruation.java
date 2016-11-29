package de.steinberg.rpc.execution.trigger.ui.configuration;

import de.steinberg.rpc.execution.trigger.core.engine.*;
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
        Listener listener1 = new DefaultListener();
        listener1.addAction(new ActionA());
        listener1.addAction(new ActionB());

        Listener listener2 = new DefaultListener();
        listener2.addAction(new ActionA ());

        Listener listener3 = new DefaultListener();
        listener3.addAction(new ActionA ());

        Monitor monitor1 = new MonitorA();
        monitor1.addListener(listener3);

        Monitor monitor2 = new MonitorB();
        monitor2.addListener(listener1);
        monitor2.addListener(listener2);

        Monitor monitor3 = new MonitorC();

        DefaultEngine engine = new DefaultEngine();
        engine.addMonitor(monitor1);
        engine.addMonitor(monitor2);
        engine.addMonitor(monitor3);
        return engine;
    }

}
