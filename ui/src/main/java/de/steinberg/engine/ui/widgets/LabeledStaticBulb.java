package de.steinberg.engine.ui.widgets;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import lombok.Getter;

/**
 * Created by lars on 26.01.2017.
 */
public class LabeledStaticBulb extends HBox {

    @Getter
    final StaticBulb bulb = new StaticBulb();

    @Getter
    final Label label = new Label();

    public LabeledStaticBulb() {
        bulb.setPrefHeight(35);
        bulb.setPrefWidth(35);
        label.setPrefHeight(35);
        label.setMaxWidth(Double.MAX_VALUE);
        setMargin(label, new Insets(0,0,0,5));
        getChildren().add(bulb);
        getChildren().add(label);
    }

    public void setColor(Color color) {
        bulb.setColor(color);
    }

    public void setText(String text) {
        label.setText(text);
    }

}
