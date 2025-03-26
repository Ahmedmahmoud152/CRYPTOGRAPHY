module org.example.playfairx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.playfairx to javafx.fxml;
    exports org.example.playfairx;
}