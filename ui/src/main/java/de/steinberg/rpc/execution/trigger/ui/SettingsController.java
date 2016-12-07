package de.steinberg.rpc.execution.trigger.ui;

import de.steinberg.rpc.execution.trigger.core.engine.Action;
import de.steinberg.rpc.execution.trigger.core.engine.Monitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Created by lkleen on 11/29/2016.
 */
public class SettingsController {

    @FXML
    ComboBox<Monitor> selectMonitor;

    @FXML
    ComboBox<Action> selectAction;

    @FXML
    AnchorPane monitorSettings;

    @FXML
    AnchorPane actionSettings;

    SettingsPaneSetup settingsPaneSetup = new SettingsPaneSetup();

    public void refreshMonitorSettings(ActionEvent actionEvent) {
        ComboBox<Monitor> comboBox = (ComboBox<Monitor>) actionEvent.getSource();
        Monitor monitor = comboBox.getSelectionModel().getSelectedItem();
        settingsPaneSetup.setup(monitorSettings, monitor.getSettings());
    }
}
