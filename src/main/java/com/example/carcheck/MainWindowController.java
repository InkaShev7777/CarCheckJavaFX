package com.example.carcheck;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.carcheck.Models.UserLogin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    private int idUser;
    @FXML
    private String Login;

    @FXML
    void initialize() {
        user_button.setOnMouseClicked(mouseEvent -> {
//            user_button.getScene().getWindow().hide();
            
            if(this.idUser == 0){
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

                //
                //  id user if authorize is success
                //
                AuthorizeController authorizeController = loader.getController();
                this.idUser = authorizeController.getIdUser();
                this.Login = authorizeController.getLogin();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Info");
                alert.setHeaderText(null);
                alert.setContentText(this.Login + " you are already logged in!\nDo you wont to log out of your account?");
                alert.showAndWait();
                if(alert.getResult() == ButtonType.OK) {
                    this.idUser = 0;
                    this.Login = "";
                }
            }
        });

        search_for_number.setOnMouseClicked(mouseEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/carcheck/search-for-number-view.fxml"));
            try{
                loader.load();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Search");
            stage.getIcons().add(new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJipxz2_UpZiqiafROnoyOwugGtvMJ7jj_lg&usqp=CAU"));
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            SearchForNumberController searchForNumberController = loader.getController();
            searchForNumberController.SetIdUser(this.idUser);
            stage.showAndWait();

        });
        search_for_vin.setOnMouseClicked(mouseEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/carcheck/search-for-vin-view.fxml"));
            try{
                loader.load();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Search");
            stage.getIcons().add(new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJipxz2_UpZiqiafROnoyOwugGtvMJ7jj_lg&usqp=CAU"));
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            SearchForVINController searchForVINController = loader.getController();
            searchForVINController.SetIdUser(this.idUser);
            stage.showAndWait();
        });
    }

}
