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

    @FXML
    void initialize() {
       ConfirmButton.setOnAction(actionEvent -> {
           if(LoginField.getText().length() > 0 && PasswordField.getText().length() > 0){
               try {
                   DBController controller = new DBController();
                   if(controller.Authorize(LoginField.getText(),PasswordField.getText())!= -1){
                       // save id User -> close this window!
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
           System.out.println("Sign Out");
           //
           // open next registration window
           //
       });
    }

}

