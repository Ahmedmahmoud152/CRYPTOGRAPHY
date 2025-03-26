module com.example.vigenere {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.vigenere to javafx.fxml;
    exports com.example.vigenere;
}