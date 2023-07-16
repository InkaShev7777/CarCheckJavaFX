package com.example.carcheck;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardSaveCarView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imageCar;

    @FXML
    private Label lastReg;

    @FXML
    private Label markModel;

    @FXML
    private Label vin;

    @FXML
    private Label year;

    @FXML
    void initialize() {

    }
    @FXML
    public void setMarkModel(String markModel){
        this.markModel.setText(markModel);
    }
    @FXML
    public void setImageCar(String url) {
        this.imageCar.setImage(new Image(url));
    }
    @FXML
    public void setLastReg(String lastReg) {
        this.lastReg.setText(lastReg);
    }
    @FXML
    public void setVin(String vin) {
        this.vin.setText(vin);
    }
@FXML
    public void setYear(String  year) {
        this.year.setText(year);
    }
}
