package de.steinberg.engine.ui;

import de.steinberg.engine.core.annotations.TooltipText;
import de.steinberg.engine.core.engine.control.Control;
import de.steinberg.engine.core.engine.control.Controls;
import de.steinberg.engine.core.engine.setting.Settings;
import de.steinberg.engine.core.engine.setting.SettingsKey;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Created by lkleen on 12/7/2016.
 */
@Slf4j
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
            button.setOnAction(event -> {
                try {
                    control.trigger();
                } catch (Exception e) {
                    log.error(e.toString());
                    throw e;
                }
            });
            addTooltipText(button, control);
            buttonsHBox.getChildren().add(button);
        }
    }

    private void addTooltipText(javafx.scene.control.Control control, Object source) {
        TooltipText tooltipText = source.getClass().getAnnotation(TooltipText.class);
        if (tooltipText == null) {return;}
        control.setTooltip(new Tooltip(tooltipText.value()));
    }

    private void addSettingsUIComponents(Settings settings, GridPane settingsPane) {
        int row = 0;
        for (Map.Entry<SettingsKey, String> entry : settings.entrySet()) {
            SettingsKey key = entry.getKey();
            Label label = new Label(key.get());
            TextField textField = new TextField(entry.getValue());
            textField.textProperty().addListener(
                    (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                        settings.put(entry.getKey(), newValue);
                    }
            );
            addTooltipText(label, key);
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
