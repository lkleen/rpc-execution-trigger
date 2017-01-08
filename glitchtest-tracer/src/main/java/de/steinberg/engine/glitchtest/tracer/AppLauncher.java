package de.steinberg.engine.glitchtest.tracer;

import de.steinberg.engine.core.configuration.CoreConfiguration;
import de.steinberg.engine.glitchtest.tracer.configuration.GlitchNotificationSenderConfiguration;
import de.steinberg.rpc.execution.trigger.ui.AppInitializer;
import de.steinberg.rpc.execution.trigger.ui.configuration.UIConfiguration;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by lkleen on 12/14/2016.
 */
public class AppLauncher extends Application {

    final AppInitializer initializer;

    public AppLauncher() {
        initializer = new AppInitializer(new AnnotationConfigApplicationContext(
                CoreConfiguration.class,
                UIConfiguration.class,
                GlitchNotificationSenderConfiguration.class
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
