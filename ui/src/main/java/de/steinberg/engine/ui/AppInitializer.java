package de.steinberg.engine.ui;

import de.steinberg.engine.core.engine.Engine;
import de.steinberg.engine.core.engine.monitor.Monitor;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by lkleen on 11/29/2016.
 */
@Slf4j
public class AppInitializer {

    final ApplicationContext applicationContext;

    public AppInitializer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setUp(Stage stage) throws Exception {
        EngineUI engineUI = applicationContext.getBean(EngineUI.class);
        Engine engine     = applicationContext.getBean(Engine.class);
        engineUI.initialize(engine, stage);
        engine.runAsync();
        log.info("engine started");
    }

    public void tearDown() {
        Engine engine = applicationContext.getBean(Engine.class);
        engine.shutdown();
        log.info("engine shut down");
    }
}
