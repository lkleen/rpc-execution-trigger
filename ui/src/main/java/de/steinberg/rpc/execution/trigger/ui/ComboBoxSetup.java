package de.steinberg.rpc.execution.trigger.ui;

import de.steinberg.rpc.execution.trigger.core.engine.SettingsAware;
import javafx.scene.control.ComboBox;

import java.util.List;

/**
 * Created by lkleen on 11/29/2016.
 */
public class ComboBoxSetup {

    public <T extends SettingsAware> void setup(List<T> entries, ComboBox<T> comboBox) {
        comboBox.setConverter(new ComboBoxStringConverter<>());

        for (T entry : entries) {
            comboBox.getItems().add(entry);
        }
        comboBox.getSelectionModel().selectFirst();
    }

}
