package com.example.carcheck;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView search_for_number;

    @FXML
    private ImageView search_for_vin;
    @FXML
    private ImageView user_button;

    @FXML
    void initialize() {
        user_button.setOnMouseClicked(mouseEvent -> {
//            user_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/carcheck/authorize-view.fxml"));
            try{
                loader.load();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Authorize");
            stage.getIcons().add(new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJipxz2_UpZiqiafROnoyOwugGtvMJ7jj_lg&usqp=CAU"));
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        search_for_number.setOnMouseClicked(mouseEvent -> {
            System.out.println("Search by number");
        });
        search_for_vin.setOnMouseClicked(mouseEvent -> {
            System.out.println("Search by VIN");
        });
    }

}
