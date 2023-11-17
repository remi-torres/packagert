module com.example.packagert {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.packagert to javafx.fxml;
    exports com.example.packagert;
    exports com.example.packagert.packager;
    opens com.example.packagert.packager to javafx.fxml;
}