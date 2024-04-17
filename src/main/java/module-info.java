module com.example.hackaton {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hackaton to javafx.fxml;
    exports com.example.hackaton;
}