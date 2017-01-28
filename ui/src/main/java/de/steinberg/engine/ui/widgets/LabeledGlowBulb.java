package de.steinberg.engine.ui.widgets;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import lombok.Getter;

/**
 * Created by lars on 26.01.2017.
 */
public class LabeledGlowBulb extends HBox {

    @Getter
    final GlowBulb glowBulb = new GlowBulb();

    @Getter
    final Label label = new Label();

    public LabeledGlowBulb() {
        glowBulb.setPrefHeight(35);
        glowBulb.setPrefWidth(35);
        label.setPrefHeight(35);
        label.setPrefWidth(150);
        setMargin(label, new Insets(0,0,0,5));
        getChildren().add(glowBulb);
        getChildren().add(label);
    }

    public void setColor(GlowBulb.Color color) {
        glowBulb.setColor(color);
    }

    public void setText(String text) {
        label.setText(text);
    }

}
