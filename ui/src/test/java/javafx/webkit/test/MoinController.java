package javafx.webkit.test;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * Created by lars on 26.01.2017.
 */
public class MoinController {

    @FXML
    Label label;

    @FXML
    public void initialize() {
    }

    public void mouseEntered(MouseEvent mouseEvent) {
        label.setId("green-glow");
    }

    public void mouseExited(MouseEvent mouseEvent) {
        label.setId("yellow-glow");
    }
}
