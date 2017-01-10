package de.steinberg.engine.glitchtest.tracer;

import de.steinberg.engine.core.configuration.CoreConfiguration;
import de.steinberg.engine.glitchtest.tracer.configuration.GlitchNotificationReceiverConfiguration;
import de.steinberg.engine.ui.AppInitializer;
import de.steinberg.engine.ui.test.configuration.UIConfiguration;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by lkleen on 12/14/2016.
 */
@Slf4j
public class AppLauncher extends Application {

    final AppInitializer initializer;

    public AppLauncher() {
        log.debug("initializing application");
        initializer = new AppInitializer(new AnnotationConfigApplicationContext(
                CoreConfiguration.class,
                UIConfiguration.class,
                GlitchNotificationReceiverConfiguration.class
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
