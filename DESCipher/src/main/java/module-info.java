module com.example.descipher {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.descipher to javafx.fxml;
    exports com.example.descipher;
}