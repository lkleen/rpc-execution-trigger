package de.steinberg.rpc.execution.trigger.slave.configuration;

import de.steinberg.rpc.execution.trigger.core.configuration.CoreConfiguration;
import de.steinberg.rpc.execution.trigger.slave.UI;
import de.steinberg.rpc.execution.trigger.slave.engine.FileAddedMonitor;
import de.steinberg.rpc.execution.trigger.slave.engine.SendGlitchNotification;
import de.steinberg.rpc.execution.trigger.slave.engine.SlaveEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by lkleen on 11/28/2016.
 */
@Configuration
@Import(CoreConfiguration.class)
public class SlaveConfiguration {

    @Bean
    public UI ui() {
        return new UI();
    }

    @Bean
    public SlaveEngine slaveEngine() {
        SlaveEngine slaveEngine = new SlaveEngine();
        slaveEngine.addMonitor(fileAddedMonitor());
        return slaveEngine;
    }

    @Bean
    public FileAddedMonitor fileAddedMonitor() {
        FileAddedMonitor fileAddedMonitor = new FileAddedMonitor();
        return fileAddedMonitor;
    }

    @Bean
    public SendGlitchNotification sendGlitchNotification() {
        return new SendGlitchNotification();
    }

}
