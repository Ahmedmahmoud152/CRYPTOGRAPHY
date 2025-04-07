package com.example.descipher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {


       DesCipher desCipher = new DesCipher();
       System.out.println(desCipher.encrypt("0123456789ABCDEF","133457799BBCDFF1"));
       System.out.println(desCipher.decrypt("85E813540F0AB405","133457799BBCDFF1"));

    }
}