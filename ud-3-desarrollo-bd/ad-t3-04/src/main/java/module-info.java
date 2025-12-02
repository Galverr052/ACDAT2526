module com.example.demo_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens iesmm.ad.t3_04 to javafx.fxml;
    exports iesmm.ad.t3_04;
    exports iesmm.ad.t3_04.controller;
    opens iesmm.ad.t3_04.controller to javafx.fxml;
    exports iesmm.ad.t3_04.model;
    opens iesmm.ad.t3_04.model to javafx.fxml;
}