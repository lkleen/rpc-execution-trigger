package de.steinberg.engine.ui;

import de.steinberg.engine.core.engine.action.Action;
import de.steinberg.engine.core.engine.monitor.Monitor;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

/**
 * Created by lkleen on 11/29/2016.
 */
public class SettingsController {

    @FXML
    ComboBox<Monitor> selectMonitor;

    @FXML
    ComboBox<Action> selectAction;

    @FXML
    VBox monitorVBox;

    @FXML
    VBox actionVBox;

    VBoxSetup vBoxSetup = new VBoxSetup();
    ComboBoxSetup comboBoxSetup = new ComboBoxSetup();

    public void refreshMonitorSettings() {
        Monitor monitor = selectMonitor.getSelectionModel().getSelectedItem();
        monitorVBox.getChildren().clear();
        vBoxSetup.setup(monitorVBox, monitor);
        updateActionComboBox();
    }

    public void refreshActionSettings() {
        Action action = selectAction.getSelectionModel().getSelectedItem();
        actionVBox.getChildren().clear();
        if (action != null) {
            vBoxSetup.setup(actionVBox, action);
        }
    }

    private void updateActionComboBox() {
        Monitor monitor = selectMonitor.getSelectionModel().getSelectedItem();
        selectAction.getItems().clear();
        comboBoxSetup.setup(monitor.getActions(), selectAction);
    }

}
