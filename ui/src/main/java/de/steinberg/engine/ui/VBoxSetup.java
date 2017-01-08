package de.steinberg.engine.ui;

import de.steinberg.engine.core.engine.Control;
import de.steinberg.engine.core.engine.Controls;
import de.steinberg.engine.core.engine.Settings;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.util.Map;

/**
 * Created by lkleen on 12/7/2016.
 */
public class VBoxSetup {

    public void setup(VBox monitorVBox, Controls controls, Settings settings) {
        if (settings != null) {
            GridPane settingsPane = createSettingsPane();
            addSettingsUIComponents(settings, settingsPane);
            monitorVBox.getChildren().add(settingsPane);
        }

        if (controls != null) {
            HBox buttonsHBox = createButtonsHBox();
            addControlsUIComponents(controls, buttonsHBox);
            monitorVBox.getChildren().add(buttonsHBox);
        }
    }

    private void addControlsUIComponents(Controls controls, HBox buttonsHBox) {
        for (Map.Entry<String, Control> entry : controls.entrySet()) {
            Button button = new Button(entry.getKey());
            Control control = entry.getValue();
            button.setOnAction(event -> {control.trigger();});
            buttonsHBox.getChildren().add(button);
        }
    }

    private void addSettingsUIComponents(Settings settings, GridPane settingsPane) {
        int row = 0;
        for (Map.Entry<String, String> entry : settings.entrySet()) {
            Label label = new Label(entry.getKey());
            TextField textField = new TextField(entry.getValue());
            textField.textProperty().addListener(
                    (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                        settings.put(entry.getKey(), newValue);
                    }
            );

            settingsPane.add(label, 0, row);
            settingsPane.add(textField, 1, row);
            row++;
        }
    }

    private GridPane createSettingsPane() {
        GridPane settingsGridPane = new GridPane();

        settingsGridPane.setGridLinesVisible(false);
        settingsGridPane.setMaxWidth(Double.MAX_VALUE);

        RowConstraints rowConstraints = new RowConstraints(30,30,30);
        settingsGridPane.getRowConstraints().add(rowConstraints);

        ColumnConstraints leftColumn = new ColumnConstraints(50, 80, 80);
        leftColumn.setHgrow(Priority.ALWAYS);
        leftColumn.setFillWidth(true);
        ColumnConstraints rightColumn = new ColumnConstraints(10, 100, Double.MAX_VALUE);
        rightColumn.setHgrow(Priority.ALWAYS);
        rightColumn.setFillWidth(true);
        settingsGridPane.getColumnConstraints().add(leftColumn);
        settingsGridPane.getColumnConstraints().add(rightColumn);

        return settingsGridPane;
    }

    private HBox createButtonsHBox() {
        HBox buttonsHBox = new HBox();
        buttonsHBox.setSpacing(3);
        return buttonsHBox;

    }
}
