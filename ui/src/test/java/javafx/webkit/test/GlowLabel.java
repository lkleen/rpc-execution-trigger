package javafx.webkit.test;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.util.Duration;

/**
 * Created by lars on 26.01.2017.
 */
public class GlowLabel extends Label {

    enum Color {
        RED,
        GREEN,
        YELLOW
    }

    public void initialize() {
        Glow glow = new Glow();
        glow.setLevel(0);
        setEffect(glow);
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        final KeyValue kv = new KeyValue(glow.levelProperty(), 0.8);
        final KeyFrame kf = new KeyFrame(Duration.millis(900), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    public void setColor(Color color) {
        switch (color) {
            case YELLOW: setId("yellow-glow"); break;
            case RED: setId("red-glow"); break;
            case GREEN: setId("green-glow"); break;
        }
    }

}
