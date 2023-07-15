package com.example.carcheck;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.carcheck.DataBase.DBController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ConfirmButton;

    @FXML
    private PasswordField ConfirmPassword;

    @FXML
    private TextField LoginField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Hyperlink backButton;
    private int IDUser;
    private  String Login;

    @FXML
    void initialize() {
        backButton.setOnMouseClicked(event -> {
            backButton.getScene().getWindow().hide();
        });
        ConfirmButton.setOnMouseClicked(event -> {
            try {
                DBController controller = new DBController();
                if(this.LoginField.getText().length() > 0 && this.PasswordField.getText().length() > 0 && this.ConfirmPassword.getText().length() > 0){
                    if(this.PasswordField.getText().equals(this.ConfirmPassword.getText())){
                        if(controller.checkNewUser(this.LoginField.getText()) == true){
                            if(controller.RegistrateUser(this.LoginField.getText(),this.PasswordField.getText()) > 0){
                                this.IDUser = controller.Authorize(this.LoginField.getText(),this.PasswordField.getText());
                                this.Login = this.LoginField.getText();
                                // ok
                            }
                        }
                        else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText(null);
                            alert.setContentText("User with this login is already registered!");
                            alert.showAndWait();
                        }
                    }
                    else {
                        // not confirm pass
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("You entered the wrong password!");
                        alert.showAndWait();
                    }
                }
                else {
                    // field
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Not all fields are filled!");
                    alert.showAndWait();
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @FXML
    public int getIDUser(){
        return this.IDUser;
    }

    public String getLogin() {
        return Login;
    }
}

