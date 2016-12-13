package de.steinberg.rpc.execution.trigger.ui;

import de.steinberg.rpc.execution.trigger.core.engine.Engine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.net.URL;

/**
 * Created by lkleen on 11/29/2016.
 */
public class EngineUI {

    @Inject
    ComboBoxSetup comboBoxSetup;

    @FXML
    public void initialize(Engine engine, Stage stage) throws Exception {
        URL resource = getClass().getClassLoader().getSystemResource("ui.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = loader.load(resource);
        Scene scene = new Scene(root, 300, 275);
        stage.setTitle("Notification Engine 0.0.1");
        stage.setScene(scene);
        stage.show();

        ComboBox selectMonitor = (ComboBox) scene.lookup("#selectMonitor");
        comboBoxSetup.setup(engine.getMonitors(), selectMonitor);
    }

}
