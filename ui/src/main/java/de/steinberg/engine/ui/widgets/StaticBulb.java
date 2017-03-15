package de.steinberg.engine.ui.widgets;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.util.Duration;

/**
 * Created by lars on 26.01.2017.
 */
public class StaticBulb extends Label {

    public void setColor(Color color) {
        switch (color) {
            case YELLOW: setId("yellow-glow"); break;
            case RED: setId("red-glow"); break;
            case GREEN: setId("green-glow"); break;
        }
    }

}
