package org;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Root extends Application {

    public static void main(String... args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL location = getClass().getResource("/org/fx/tool/view/base/Base.fxml");

        Parent root = FXMLLoader.load(location);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
