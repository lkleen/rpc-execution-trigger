package de.steinberg.engine.ui;

import de.steinberg.engine.core.engine.Engine;
import de.steinberg.engine.core.messaging.BlockingMessageQueue;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.net.URL;

/**
 * Created by lkleen on 11/29/2016.
 */
public class EngineUI {

    @Inject
    ComboBoxSetup comboBoxSetup;

    @Inject
    BlockingMessageQueue blockingMessageQueue;

    @Inject
    OutputToTextAreaService outputToTextAreaService;

    public void initialize(Engine engine, Stage stage) throws Exception {
        URL resource = getClass().getClassLoader().getSystemResource("ui.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = loader.load(resource);
        Scene scene = new Scene(root, 800, 600);

        URL css = getClass().getClassLoader().getSystemResource("glow-bulb-style.css");
        scene.getStylesheets().add(css.toString());

        stage.setTitle("Notification Engine 0.0.1");
        stage.setScene(scene);
        stage.show();
        MessageQueueAppender.messageQueue = blockingMessageQueue;
        ComboBox selectMonitor = (ComboBox) scene.lookup("#selectMonitor");
        comboBoxSetup.setup(engine.getMonitors(), selectMonitor);
        TextArea output = (TextArea) scene.lookup("#output");
        outputToTextAreaService.setOutput(output);
        outputToTextAreaService.start();
    }

}
