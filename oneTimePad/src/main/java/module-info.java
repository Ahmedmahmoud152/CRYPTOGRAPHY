module com.example.onetimepad {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.onetimepad to javafx.fxml;
    exports com.example.onetimepad;
}