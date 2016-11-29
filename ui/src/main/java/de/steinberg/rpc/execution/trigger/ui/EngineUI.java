package de.steinberg.rpc.execution.trigger.ui;

import de.steinberg.rpc.execution.trigger.core.engine.Engine;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;

/**
 * Created by lkleen on 11/29/2016.
 */
public class EngineUI {

    public void initialize(Engine engine, Stage stage) throws Exception {
        URL resource = getClass().getClassLoader().getSystemResource("ui.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root, 300, 275);

        ComboBox comboBox = (ComboBox) scene.lookup("#selectMonitor");
        initializeComboBox(comboBox, engine.getMonitors());

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }

    private void initializeComboBox(ComboBox comboBox, List<?> entries) {
        for (Object entry : entries) {
            comboBox.getItems().add(entry.getClass().getSimpleName());
        }
        if (entries.size() > 0) {
            comboBox.getSelectionModel().select(0);
        }
    }

}
