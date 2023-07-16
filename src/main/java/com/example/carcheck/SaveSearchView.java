package com.example.carcheck;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class SaveSearchView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label buttonBack;

    @FXML
    private GridPane mainWindow;

    @FXML
    void initialize() throws IOException {
        this.buttonBack.setOnMouseClicked(event -> {
            this.buttonBack.getScene().getWindow().hide();
        });
        for(int i =0;i<5;i++){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("card-save-car-view.fxml"));
            AnchorPane box = loader.load();
            CardSaveCarView cardSaveCarView = loader.getController();
            cardSaveCarView.setMarkModel("Test" + i);
//            cardSaveCarView.setImageCar("https://baza-gai.com.ua/catalog-images/volkswagen/tiguan/II/image.jpg");
            mainWindow.add(box,0,i);
        }

    }

}
