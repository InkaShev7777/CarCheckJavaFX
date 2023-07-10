module com.example.carcheck {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.carcheck to javafx.fxml;
    exports com.example.carcheck;
}