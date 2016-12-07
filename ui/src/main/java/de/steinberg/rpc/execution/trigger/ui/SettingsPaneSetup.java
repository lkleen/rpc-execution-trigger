package de.steinberg.rpc.execution.trigger.ui;

import de.steinberg.rpc.execution.trigger.core.engine.Settings;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.Map;

/**
 * Created by lkleen on 12/7/2016.
 */
public class SettingsPaneSetup {

    public void setup(Pane pane, Settings settings) {
        pane.getChildren().clear();

        if (settings == null) {
            return;
        }

        GridPane gridPane = new GridPane();
        int row = 0;

        for (Map.Entry<String, String> entry : settings.entrySet()) {
            Label label = new Label(entry.getKey());
            TextField textField = new TextField(entry.getValue());
            gridPane.add(label, 0, row);
            gridPane.add(textField, 1, row);
            row++;
        }

        pane.getChildren().add(gridPane);
    }

}
