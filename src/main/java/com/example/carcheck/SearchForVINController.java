package com.example.carcheck;

import java.net.URL;
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

public class SearchForVINController {

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
    private TextField VIN;


    @FXML
    void  initialize() {
        buttonBack.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) buttonBack.getScene().getWindow();
            stage.close();
        });
        searchButton.setOnMouseClicked(mouseEvent -> {
            System.out.println(this.VIN.getText());
            if(this.VIN.getText().length() == 17){
                APIController api = new APIController();
                api.searchByVIN(this.VIN.getText().toUpperCase());
                if(api.getError() != true){
                    this.Marck.setText(api.getMark());
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
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Something went wrong. The VIN must contain 17 characters!");
                alert.showAndWait();
            }
        });
        this.saveInDB.setOnMouseClicked(event -> {
            //
            //  Save in DB
            //
        });
    }

}
