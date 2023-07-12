package com.example.carcheck;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.carcheck.API.APIController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SearchForNumberController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text Marck;

    @FXML
    private Label buttonBack;

    @FXML
    private ImageView imageCar;

    @FXML
    private ImageView saveButton;

    @FXML
    private ImageView searchButton;

    @FXML
    private Text carYear;

    @FXML
    private Text vin;

    @FXML
    private Text lastReg;

    @FXML
    private ImageView saveInDB;

    @FXML
    private TextField number;

    @FXML
    void initialize() {
       buttonBack.setOnMouseClicked(mouseEvent -> {
           Stage stage = (Stage) buttonBack.getScene().getWindow();
           stage.close();
       });
        searchButton.setOnMouseClicked(mouseEvent -> {
            APIController api = new APIController();
            String number = this.number.getText();
            if(number.length() == 8){
                if(number.charAt(0) >= 'A' && number.charAt(1) >= 'A' && number.charAt(6) >= 'A' && number.charAt(7) >= 'A'){
                    api.searchByNumber(number.toUpperCase());
                    if(api.getError() != true){
                        this.Marck.setText(api.getMarck());
                        this.imageCar.setImage(new Image(api.getURLImage()));
                        this.carYear.setText(api.getYear());
                        this.vin.setText("VIN: " + api.getVIN());
                        this.lastReg.setText("Last registration: " + api.getLastRegistrate());
                        this.saveInDB.setImage(new Image("file:/Users/ilyaschevchenko/Desktop/CarCheck/src/main/source/save.png"));
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Error!");
                        alert.showAndWait();
                    }
                }
                else {
                    ErrorAlert();
                }
            }
            else {
               ErrorAlert();
            }
        });
        this.saveInDB.setOnMouseClicked(event -> {
            //
            //  send date in DB
            //
        });
    }
    public void ErrorAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Something went wrong. The number should look like this: AE7777EA");
        alert.showAndWait();
    }

}

