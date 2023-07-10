package com.example.carcheck;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
               System.out.println("Ok");
           }
           else {
               //
               // add window with alert
               //
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
