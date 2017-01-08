package de.steinberg.engine.ui.test;

import de.steinberg.engine.core.configuration.CoreConfiguration;
import de.steinberg.engine.ui.AppInitializer;
import de.steinberg.engine.ui.test.configuration.UITestConfigruation;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by lkleen on 12/14/2016.
 */
public class TestAppLauncher extends Application {

    final AppInitializer initializer;

    public TestAppLauncher() {
        initializer = new AppInitializer(new AnnotationConfigApplicationContext(
                CoreConfiguration.class,
                UITestConfigruation.class
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
