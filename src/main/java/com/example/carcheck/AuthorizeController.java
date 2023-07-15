package com.example.carcheck;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.carcheck.DataBase.DBController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AuthorizeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ConfirmButton;

    @FXML
    private TextField LoginField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Hyperlink SignOutLink;
    private int idUser;
    private String Login;

    @FXML
    void initialize() {
       ConfirmButton.setOnAction(actionEvent -> {
           if(LoginField.getText().length() > 0 && PasswordField.getText().length() > 0){
               try {
                   DBController controller = new DBController();
                   int idNow = controller.Authorize(LoginField.getText(),PasswordField.getText());
                   if(idNow!= -1){
                       idUser = idNow;
                       Login = LoginField.getText();
                       this.ConfirmButton.getScene().getWindow().hide();
                   }
                   else {
                       // Alert error
                   }
               } catch (ClassNotFoundException e) {
                   throw new RuntimeException(e);
               } catch (SQLException e) {
                   throw new RuntimeException(e);
               }
               //
               // check from DB
               //

           }
           else {
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error");
               alert.setHeaderText(null);
               alert.setContentText("Login or Password is empty!");

               alert.showAndWait();
           }
       });
       SignOutLink.setOnAction(actionEvent -> {
           //
           // open next registration window
           //
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("/com/example/carcheck/registration-view.fxml"));
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
           SignOutLink.getScene().getWindow().hide();

           RegistrationController controller = loader.getController();
           this.idUser = controller.getIDUser();
           this.Login = controller.getLogin();
           stage.show();
//           stage.showAndWait();
       });
    }
    @FXML
    public int getIdUser(){
        return  this.idUser;
    }
    @FXML
    public String getLogin(){
        return  this.Login;
    }

}

