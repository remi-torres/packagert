module com.example.packagert {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.packagert to javafx.fxml;
    exports com.example.packagert;
    exports com.example.packagert.project;
    opens com.example.packagert.project to javafx.fxml;
    exports com.example.packagert.feature;
    opens com.example.packagert.feature to javafx.fxml;
    exports com.example.packagert.common;
    opens com.example.packagert.common to javafx.fxml;
    exports com.example.packagert.generator;
    opens com.example.packagert.generator to javafx.fxml;

}