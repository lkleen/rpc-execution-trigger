package javafx.webkit.test;

import de.steinberg.engine.ui.widgets.Color;
import de.steinberg.engine.ui.widgets.GlowBulb;
import de.steinberg.engine.ui.widgets.LabeledGlowBulb;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Created by lars on 26.01.2017.
 */
public class MoinController {

    @FXML
    Pane main;

    LabeledGlowBulb label;

    @FXML
    public void initialize() {
        label = new LabeledGlowBulb();
        label.setOnMouseEntered((MouseEvent m) -> mouseEntered(m));
        label.setOnMouseExited((MouseEvent m) -> mouseExited(m));
        main.getChildren().add(label);
        label.setColor(Color.YELLOW);
        label.setText("yellow");
    }

    public void mouseEntered(MouseEvent mouseEvent) {
        label.setColor(Color.GREEN);
        label.setText("green");
    }

    public void mouseExited(MouseEvent mouseEvent) {
        label.setColor(Color.RED);
        label.setText("red");
    }
}
