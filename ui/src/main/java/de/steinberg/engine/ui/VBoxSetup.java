package de.steinberg.engine.ui;

import de.steinberg.engine.core.annotations.TooltipText;
import de.steinberg.engine.core.engine.Parametrized;
import de.steinberg.engine.core.engine.control.Control;
import de.steinberg.engine.core.engine.control.Controls;
import de.steinberg.engine.core.engine.selection.SelectionList;
import de.steinberg.engine.core.engine.selection.Selections;
import de.steinberg.engine.core.engine.setting.Settings;
import de.steinberg.engine.core.engine.setting.SettingsKey;
import de.steinberg.engine.core.engine.status.Status;
import de.steinberg.engine.ui.widgets.GlowBulb;
import de.steinberg.engine.ui.widgets.LabeledGlowBulb;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Created by lkleen on 12/7/2016.
 */
@Slf4j
public class VBoxSetup {

    public void setup(VBox vbox, Parametrized parametrized) {
        Selections selections = parametrized.getSelections();
        if (selections != null) {
            int row = 0;
            GridPane selectionsGridPane = createGridPane();
            for (Map.Entry<String, SelectionList> entry : selections.entrySet()) {
                addSelectionsUIComponents(entry, selectionsGridPane, row);
                row++;
            }
            vbox.getChildren().add(selectionsGridPane);
        }

        Settings settings = parametrized.getSettings();
        if (settings != null) {
            GridPane settingsPane = createGridPane();
            addSettingsUIComponents(settings, settingsPane);
            vbox.getChildren().add(settingsPane);
        }

        Controls controls = parametrized.getControls();
        if (controls != null) {
            HBox buttonsHBox = createHBox();
            addControlsUIComponents(controls, buttonsHBox);
            vbox.getChildren().add(buttonsHBox);
        }

        Status status = parametrized.getStatus();
        if (status != null) {
            LabeledGlowBulb glowBulb = new LabeledGlowBulb();
            glowBulb.setColor(getGlowColorFrom(status.getColor()));
            glowBulb.setText(status.getText());
            status.addListener((observable, oldValue, newValue) -> {
                glowBulb.setColor(getGlowColorFrom(newValue.getColor()));
                glowBulb.setText(newValue.getText());
            });
            HBox statusHBox = createHBox();
            statusHBox.getChildren().add(glowBulb);
            vbox.getChildren().add(statusHBox);
        }
    }

    private GlowBulb.Color getGlowColorFrom(Status.Color color) {
        switch (color) {
            case GREEN: return GlowBulb.Color.GREEN;
            case YELLOW: return GlowBulb.Color.YELLOW;
            case RED: return GlowBulb.Color.RED;
        }
        throw new IllegalArgumentException("color no supported");
    }

    private void addSelectionsUIComponents(Map.Entry<String, SelectionList> entry, GridPane selectionsGridPane, int row) {
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setMaxWidth(Double.MAX_VALUE);
        for (String choice : entry.getValue()) {
            choiceBox.getItems().add(choice);
        }
        choiceBox.valueProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) -> entry.getValue().setSelected(newValue)
        );
        choiceBox.getSelectionModel().selectFirst();
        Label label = new Label(entry.getKey());
        selectionsGridPane.add(label, 0, row);
        selectionsGridPane.add(choiceBox, 1, row);
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

    private GridPane createGridPane() {
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

    private HBox createHBox() {
        HBox buttonsHBox = new HBox();
        buttonsHBox.setSpacing(3);
        return buttonsHBox;

    }
}
