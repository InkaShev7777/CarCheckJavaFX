module com.example.carcheck {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires java.net.http;
    requires com.google.gson;


    opens com.example.carcheck to javafx.fxml;
    exports com.example.carcheck;
}