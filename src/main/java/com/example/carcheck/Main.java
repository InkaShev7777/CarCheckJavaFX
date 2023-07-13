package com.example.carcheck;

import com.example.carcheck.DataBase.DBController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public  void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-window-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 500);
        stage.getIcons().add(new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJipxz2_UpZiqiafROnoyOwugGtvMJ7jj_lg&usqp=CAU"));
        stage.setTitle("CarCheck");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        launch();
    }
}
