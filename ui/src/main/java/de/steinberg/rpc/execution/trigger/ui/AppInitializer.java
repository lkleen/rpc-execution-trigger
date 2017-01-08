package de.steinberg.rpc.execution.trigger.ui;

import de.steinberg.engine.core.engine.Engine;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

/**
 * Created by lkleen on 11/29/2016.
 */
public class AppInitializer {

    final ApplicationContext applicationContext;

    public AppInitializer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setUp(Stage stage) throws Exception {
        EngineUI engineUI = applicationContext.getBean(EngineUI.class);
        Engine engine     = applicationContext.getBean(Engine.class);
        engineUI.initialize(engine, stage);
    }

    public void tearDown() {
        EngineUI engineUI = applicationContext.getBean(EngineUI.class);
        engineUI.shutdown();
    }
}
