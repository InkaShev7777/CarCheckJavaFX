package com.example.carcheck;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.carcheck.API.APIController;
import com.example.carcheck.DataBase.DBController;
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
    private int IDUser;
    private String urlDB;
    private String vinDB;
    private String markDB;
    private String modelDB;
    private String lastRegDB;
    private String yearDB;

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
                        this.Marck.setText(api.getMark() + " " + api.getModel());
                        this.markDB = api.getMark();

                        this.modelDB = api.getModel();

                        this.imageCar.setImage(new Image(api.getURLImage()));
                        this.urlDB = api.getURLImage();

                        this.carYear.setText(api.getYear());
                        this.yearDB = api.getYear();

                        this.urlDB = api.getURLImage();

                        this.vin.setText("VIN: " + api.getVIN());
                        this.vinDB = api.getVIN();

                        this.lastReg.setText("Last registration: " + api.getLastRegistrate());
                        this.lastRegDB = api.getLastRegistrate();

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
            if(this.IDUser != 0){
                try {
                    DBController controller = new DBController();
                   int cod =  controller.SaveSearchInDB(this.IDUser,this.markDB,this.modelDB,this.yearDB,this.vinDB,this.lastRegDB,this.urlDB);
                   if(cod > 0){
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                       alert.setTitle("Information");
                       alert.setHeaderText(null);
                       alert.setContentText("Your search result has been saved successfully");
                       alert.showAndWait();
                   }
                   else {
                       Alert alert = new Alert(Alert.AlertType.ERROR);
                       alert.setTitle("Error");
                       alert.setHeaderText(null);
                       alert.setContentText("Something went wrong");
                       alert.showAndWait();
                   }
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Something went wrong. You must be logged in to save your search!");
                alert.showAndWait();
            }
        });
    }
    public void ErrorAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Something went wrong. The number should look like this: AE7777EA");
        alert.showAndWait();
    }
    @FXML
    public void SetIdUser(int id){
        this.IDUser = id;
    }

}

