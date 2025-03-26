module org.example.hillcipherx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.hillcipherx to javafx.fxml;
    exports org.example.hillcipherx;
}