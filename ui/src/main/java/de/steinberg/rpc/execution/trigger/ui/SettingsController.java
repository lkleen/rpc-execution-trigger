package de.steinberg.rpc.execution.trigger.ui;

import de.steinberg.rpc.execution.trigger.core.engine.Action;
import de.steinberg.rpc.execution.trigger.core.engine.Monitor;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.util.Map;

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


    VBoxSetup vBoxSetup = new VBoxSetup();

    public void refreshMonitorSettings() {
        Monitor monitor = selectMonitor.getSelectionModel().getSelectedItem();
        vBoxSetup.setup(monitorVBox, monitor.getControls(), monitor.getSettings());
        updateActionComboBox();
    }

    public void refreshActionSettings() {

    }

    private void updateActionComboBox() {
        Monitor monitor = selectMonitor.getSelectionModel().getSelectedItem();
        selectAction.getItems().clear();

        for (Action action : monitor.getActions()) {
            selectAction.getItems().add(action);
        }

        if (monitor.getActions().size() > 0) {
            selectAction.getSelectionModel().selectFirst();
        }

    }

}
