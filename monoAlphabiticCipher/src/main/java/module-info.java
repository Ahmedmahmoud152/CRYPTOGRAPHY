module org.example.monoalphabiticcipher {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.monoalphabiticcipher to javafx.fxml;
    exports org.example.monoalphabiticcipher;
}