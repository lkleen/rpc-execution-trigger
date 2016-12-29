package de.steinberg.rpc.execution.trigger.ui.configuration;

import de.steinberg.rpc.execution.trigger.ui.ComboBoxSetup;
import de.steinberg.rpc.execution.trigger.ui.EngineUI;
import de.steinberg.rpc.execution.trigger.ui.OutputToTextAreaService;
import de.steinberg.rpc.execution.trigger.ui.OutputToTextAreaTask;
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

    @Bean
    public ComboBoxSetup comboBoxWithSettings() {
        return new ComboBoxSetup();
    }

    @Bean
    public OutputToTextAreaTask outputToTextAreaTask() {
        return new OutputToTextAreaTask();
    }

    @Bean
    public OutputToTextAreaService outputToTextAreaService() {
        return new OutputToTextAreaService();
    }

}
