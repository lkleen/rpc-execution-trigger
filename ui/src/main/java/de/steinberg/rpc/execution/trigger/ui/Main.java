package de.steinberg.rpc.execution.trigger.ui;

import de.steinberg.rpc.execution.trigger.core.configuration.CoreConfiguration;
import de.steinberg.rpc.execution.trigger.ui.configuration.UIConfiguration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.URL;

/**
 * Created by lkleen on 11/29/2016.
 */
public class Main extends Application{

    public static void main(String... args) throws Exception {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                CoreConfiguration.class,
                UIConfiguration.class
        );
        EngineUI engineUI = applicationContext.getBean(EngineUI.class);

    }
}
