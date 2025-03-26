module org.example.cesarcipherx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.cesarcipherx to javafx.fxml;
    exports org.example.cesarcipherx;
}