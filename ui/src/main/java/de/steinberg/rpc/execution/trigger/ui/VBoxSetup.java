package de.steinberg.rpc.execution.trigger.ui;

import de.steinberg.rpc.execution.trigger.core.engine.Controller;
import de.steinberg.rpc.execution.trigger.core.engine.Controllers;
import de.steinberg.rpc.execution.trigger.core.engine.Monitor;
import de.steinberg.rpc.execution.trigger.core.engine.Settings;
import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.util.Map;

/**
 * Created by lkleen on 12/7/2016.
 */
public class VBoxSetup {

    public void setup(VBox monitorVBox, Monitor monitor) {

        monitorVBox.getChildren().clear();

        GridPane settingsPane = createSettingsPane();
        addSettingsUIComponents(monitor, settingsPane);
        monitorVBox.getChildren().add(settingsPane);

        HBox buttonsHBox = createButtonsHBox();
        addControlsUIComponents(monitor, buttonsHBox);
        monitorVBox.getChildren().add(buttonsHBox);

    }

    private void addControlsUIComponents(Monitor monitor, HBox buttonsHBox) {
        for (Map.Entry<String, Controller> entry : monitor.getControllers().entrySet()) {
            Button button = new Button(entry.getKey());
            Controller controller = entry.getValue();
            controller.setSettings(monitor.getSettings());
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controller.trigger();
                }
            });
            buttonsHBox.getChildren().add(button);
        }
    }

    private void addSettingsUIComponents(Monitor monitor, GridPane settingsPane) {
        int row = 0;
        for (Map.Entry<String, String> entry : monitor.getSettings().entrySet()) {
            Label label = new Label(entry.getKey());
            TextField textField = new TextField(entry.getValue());
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
