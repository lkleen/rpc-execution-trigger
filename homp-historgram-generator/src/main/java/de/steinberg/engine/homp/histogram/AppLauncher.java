package de.steinberg.engine.homp.histogram;

import de.steinberg.engine.core.configuration.CoreConfiguration;
import de.steinberg.engine.homp.histogram.configuration.HompHistogramGeneratorConfiguration;
import de.steinberg.engine.ui.AppInitializer;
import de.steinberg.engine.ui.test.configuration.UIConfiguration;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class AppLauncher extends Application {

    final AppInitializer initializer;

    public AppLauncher() {
        log.debug("initializing application");
        initializer = new AppInitializer(new AnnotationConfigApplicationContext(
                CoreConfiguration.class,
                UIConfiguration.class,
                HompHistogramGeneratorConfiguration.class
        ));
    }

    @Override
    public void start(Stage stage) throws Exception {
        initializer.setUp(stage);
    }

    @Override
    public void stop() {
        initializer.tearDown();
    }

    public static void main(String... args) throws Exception {
        launch(args);
    }
}
