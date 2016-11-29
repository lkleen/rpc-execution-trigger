package de.steinberg.rpc.execution.trigger.ui;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lkleen on 11/29/2016.
 */
@Configuration
public class UIConfiguration {

    @Bean
    public EngineUI engineUI() {
        return new EngineUI();
    }

}
