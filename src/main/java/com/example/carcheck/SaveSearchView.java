package com.example.carcheck;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.example.carcheck.DataBase.DBController;
import com.example.carcheck.Models.Car;
import com.example.carcheck.Storage.UserLogin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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

    private List<Car> carList;

    @FXML
    void initialize() throws IOException, SQLException, ClassNotFoundException {
        DBController controller = new DBController();
        carList = controller.getListCars(UserLogin.getIdUser());
        ShowListSaveCars();
        this.buttonBack.setOnMouseClicked(event -> {
            this.buttonBack.getScene().getWindow().hide();
        });
    }
    private void ShowListSaveCars() throws IOException, SQLException, ClassNotFoundException {
        int i = 0;
        DBController controller = new DBController();
        ClearMainWindow();
        for(Car c:carList){

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("card-save-car-view.fxml"));
            AnchorPane box = loader.load();
            CardSaveCarView cardSaveCarView = loader.getController();
            cardSaveCarView.setMarkModel(c.getMark() + " " + c.getModel());
            cardSaveCarView.setImageCar(c.getURL_Photo());
            cardSaveCarView.setVin(c.getVIN());
            cardSaveCarView.setYear(c.getYear());
            cardSaveCarView.setLastReg(c.getLastRegistration());
            box.setOnMouseClicked(event -> {
                //
                //  Alert ? delete this car or no
                //
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Info");
                alert.setHeaderText(null);
                alert.setContentText("Do you wont to delete: " + c.getMark() + " " + c.getModel());
                alert.showAndWait();
                if(alert.getResult() == ButtonType.OK) {
                    try {
                        controller.DeleteCar(c.getId());
                        DeleteFromList(c.getId());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            mainWindow.add(box,0,i);
            i++;
        }
    }
    private void DeleteFromList(int id) throws SQLException, IOException, ClassNotFoundException {
        for(Car c : carList){
            if(c.getId() == id){
                carList.remove(c);
                break;
            }
        }
        ShowListSaveCars();
    }
    private void ClearMainWindow(){
        mainWindow.getChildren().clear();
    }
}
