package javafx.webkit.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Created by lars on 26.01.2017.
 */
public class Moin extends Application {

    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL resource = getClass().getClassLoader().getSystemResource("status-lamp-test.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = loader.load(resource);
        Scene scene = new Scene(root, 800, 600);

        URL css = getClass().getClassLoader().getSystemResource("style.css");
        scene.getStylesheets().add(css.toString());

        stage.setTitle("test playground");
        stage.setScene(scene);
        stage.show();

        drawLamp(stage);
    }

    private void drawLamp(Stage stage) {
        //Pane pane = stage.
    }

}
