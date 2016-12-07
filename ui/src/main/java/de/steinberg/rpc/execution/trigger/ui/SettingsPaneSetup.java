package de.steinberg.rpc.execution.trigger.ui;

import de.steinberg.rpc.execution.trigger.core.engine.Settings;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.util.Map;

/**
 * Created by lkleen on 12/7/2016.
 */
public class SettingsPaneSetup {

    public void setup(AnchorPane pane, Settings settings) {
        pane.getChildren().clear();

        if (settings == null) {
            return;
        }



        GridPane gridPane = new GridPane();
        int row = 0;

        AnchorPane.setBottomAnchor(gridPane, 0.0);
        AnchorPane.setLeftAnchor(gridPane, 0.0);
        AnchorPane.setRightAnchor(gridPane, 0.0);
        AnchorPane.setTopAnchor(gridPane, 0.0);

        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.setGridLinesVisible(true);
        ColumnConstraints leftColumn = new ColumnConstraints(10, 100, Double.MAX_VALUE);
        leftColumn.setHgrow(Priority.ALWAYS);
        leftColumn.setFillWidth(true);
        ColumnConstraints rightColumn = new ColumnConstraints(10, 100, Double.MAX_VALUE);
        rightColumn.setHgrow(Priority.ALWAYS);
        rightColumn.setFillWidth(true);
        gridPane.getColumnConstraints().add(leftColumn);
        gridPane.getColumnConstraints().add(rightColumn);

        for (Map.Entry<String, String> entry : settings.entrySet()) {
            Label label = new Label(entry.getKey());
            TextField textField = new TextField(entry.getValue());
            //textField.setMaxWidth(Double.MAX_VALUE);
            gridPane.add(label, 0, row);
            gridPane.add(textField, 1, row);
            row++;
        }

        pane.getChildren().add(gridPane);
    }

}
