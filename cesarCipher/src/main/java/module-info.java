module org.example.demo5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.cesarCipher to javafx.fxml;
    exports org.example.cesarCipher;
}