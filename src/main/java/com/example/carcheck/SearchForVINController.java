package com.example.carcheck;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.carcheck.API.APIController;
import com.example.carcheck.DataBase.DBController;
import com.example.carcheck.Storage.UserLogin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    private int IDUser;
    private String urlDB;
    private String vinDB;
    private String markDB;
    private String modelDB;
    private String lastRegDB;
    private String yearDB;


    @FXML
    void  initialize() {
        buttonBack.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) buttonBack.getScene().getWindow();
            stage.close();
        });
        searchButton.setOnMouseClicked(mouseEvent -> {
            if(this.VIN.getText().length() == 17){
                APIController api = new APIController();
                api.searchByVIN(this.VIN.getText().toUpperCase());
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
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Something went wrong. The VIN must contain 17 characters!");
                alert.showAndWait();
            }
        });
        this.saveInDB.setOnMouseClicked(event -> {
            if(UserLogin.getIdUser() != 0){
                try {
                    DBController controller = new DBController();
                    int cod =  controller.SaveSearchInDB(UserLogin.getIdUser(),this.markDB,this.modelDB,this.yearDB,this.vinDB,this.lastRegDB,this.urlDB);
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
        this.saveButton.setOnMouseClicked(event -> {
            if(UserLogin.getIdUser() != 0){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/carcheck/save-search-view.fxml"));
                try{
                    loader.load();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("Save");
                stage.getIcons().add(new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJipxz2_UpZiqiafROnoyOwugGtvMJ7jj_lg&usqp=CAU"));
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("You must be logged in to view saved searches!");
                alert.show();
            }
        });
    }
}
