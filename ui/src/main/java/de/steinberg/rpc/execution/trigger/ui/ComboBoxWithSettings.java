package de.steinberg.rpc.execution.trigger.ui;

import de.steinberg.rpc.execution.trigger.core.annotations.DisplayNameResolver;
import de.steinberg.rpc.execution.trigger.core.engine.SettingsAware;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by lkleen on 11/29/2016.
 */
public class ComboBoxWithSettings {

    @Inject
    DisplayNameResolver displayNameResolver;

    public void setup(List<? extends SettingsAware> entries, ComboBox comboBox, Pane settingsPane) {
        for (Object entry : entries) {
            comboBox.getItems().add(displayNameResolver.resolveFrom(entry));
        }
        if (entries.size() > 0) {
            comboBox.getSelectionModel().select(0);
        }
    }

}
