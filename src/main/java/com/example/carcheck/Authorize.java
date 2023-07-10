package com.example.carcheck;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Authorize extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Authorize.class.getResource("authorize-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 500);
        stage.setTitle("CarCheck");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
