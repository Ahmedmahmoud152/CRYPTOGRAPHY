package org.example.hillcipherx;

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

        launch();
        int [][]mat=new int[2][2];
        mat[0][0]=3;
        mat[0][1]=3;
        mat[1][0]=2;
        mat[1][1]=5;
        int []vect=new int[2];
        vect[0]=7;
        vect[1]=0;


        Matrix matrix=new Matrix(mat,vect);

         vect=matrix.multiply(mat,vect);

        for (int i = 0; i < vect.length; i++) {
            System.out.println(vect[i]);
        }
        System.out.println(matrix.det(mat));
    }
}