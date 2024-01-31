module com.triqui {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.triqui to javafx.fxml;
    exports com.triqui;
    exports com.triqui.controller;
    opens com.triqui.controller to javafx.fxml;
}