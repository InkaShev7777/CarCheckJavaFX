package com.example.carcheck;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SearchForVINController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label buttonBack;

    @FXML
    private ImageView searchButton;

    @FXML
    private ImageView saveButton;

    @FXML
    private Text text;

    @FXML
    void initialize() {
        buttonBack.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) buttonBack.getScene().getWindow();
            stage.close();
        });
        searchButton.setOnMouseClicked(mouseEvent -> {
            this.text.setText("Rot twoy imel!");
        });
    }

}
