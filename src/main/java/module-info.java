module com.example.hackaton {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.slf4j;
    requires com.jfoenix;
    requires java.desktop;


    opens com.example.hackaton to javafx.fxml;
    exports com.example.hackaton;
}