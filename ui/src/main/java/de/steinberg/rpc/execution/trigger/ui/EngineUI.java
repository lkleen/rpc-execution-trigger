package de.steinberg.rpc.execution.trigger.ui;

import ch.qos.logback.classic.spi.LoggingEvent;
import de.steinberg.rpc.execution.trigger.core.engine.Engine;
import de.steinberg.rpc.execution.trigger.core.messaging.BlockingMessageQueue;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lkleen on 11/29/2016.
 */
public class EngineUI {

    @Inject
    ComboBoxSetup comboBoxSetup;

    @Inject
    BlockingMessageQueue blockingMessageQueue;

    @Inject
    OutputToTextAreaTask outputToTextAreaTask;

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void initialize(Engine engine, Stage stage) throws Exception {
        URL resource = getClass().getClassLoader().getSystemResource("ui.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = loader.load(resource);
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Notification Engine 0.0.1");
        stage.setScene(scene);
        stage.show();
        MessageQueueAppender.messageQueue = blockingMessageQueue;
        ComboBox selectMonitor = (ComboBox) scene.lookup("#selectMonitor");
        comboBoxSetup.setup(engine.getMonitors(), selectMonitor);
        TextArea output = (TextArea) scene.lookup("#output");
        outputToTextAreaTask.setOutput(output);
        executorService.submit(outputToTextAreaTask);
    }

    public void shutdown() {
        outputToTextAreaTask.cancel(true);
        executorService.shutdown();
    }
}
