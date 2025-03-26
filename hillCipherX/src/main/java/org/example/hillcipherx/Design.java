package org.example.hillcipherx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Design extends Application {
    private final hillCipher cipher = new hillCipher();
    private final TextField encryptionText = new TextField();
    private final TextField decryptionText = new TextField();
    private final TextField key1 = new TextField();
    private final TextField key2 = new TextField();
    private final TextField key3 = new TextField();
    private final TextField key4 = new TextField();

    Label encryptionL = createLabel("PlainText");
    Label decryptionL = createLabel("CipherText");
    Label matrixKeyL = createLabel("KEY");


    Label ltShow1 = createLabel("");
    Label ltShow2 = createLabel("");

    Button ENC = new Button("ENC");
    Button DEC = new Button("DEC");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Encryption/Decryption UI");


        AnchorPane anchorPane = new AnchorPane();


        labelDesign(encryptionL, 80, 100, 105, 75, 2, 2);

        textDesign(encryptionText, 300.0, 120.0, 155, 25, 2, 2);

        labelDesign(matrixKeyL, 80, 350, 105, 75, 2, 2);
        textDesign(key1, 300.0, 320.0, 50, 50, 2, 2);
        textDesign(key2, 410.0, 320.0, 50, 50, 2, 2);
        textDesign(key3, 300.0, 430.0, 50, 50, 2, 2);
        textDesign(key4, 410.0, 430.0, 50, 50, 2, 2);


        labelDesign(decryptionL, 80, 700, 105, 75, 2, 2);
        textDesign(decryptionText, 300.0, 720.0, 155, 25, 2, 2);

        AnchorPane.setLeftAnchor(ENC, 600.0);
        AnchorPane.setTopAnchor(ENC, 50.0);

        AnchorPane.setLeftAnchor(DEC, 600.0);
        AnchorPane.setTopAnchor(DEC, 200.0);

        ENC.setOnAction(event -> encryptText());
        DEC.setOnAction(event -> decryptText());


        anchorPane.setBackground(Background.fill(Color.WHITE));

        anchorPane.getChildren().addAll(ENC, DEC, encryptionL, encryptionText, decryptionL, decryptionText, key1, key2, key3, key4, matrixKeyL);


        Scene scene = new Scene(anchorPane, 1200, 1000, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void labelDesign(Label label, double dx, double dy, int wx, int wy, int sx, int sy) {

        label.setPrefWidth(wx);
        label.setPrefHeight(wy);
        label.setScaleX(sx);
        label.setScaleY(sy);
        AnchorPane.setLeftAnchor(label, dx);
        AnchorPane.setTopAnchor(label, dy);

    }

    private void textDesign(TextField textField, double dx, double dy, int wx, int wy, int sx, int sy) {
        textField.setAlignment(Pos.CENTER);
        textField.setPrefWidth(wx);
        textField.setPrefHeight(wy);
        textField.setScaleX(sx);
        textField.setScaleY(sy);
        AnchorPane.setLeftAnchor(textField, dx);
        AnchorPane.setTopAnchor(textField, dy);

    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", 18));
        label.setTextFill(Color.BLACK);
        return label;
    }

    private void encryptText() {
        Long STARTTIME = System.nanoTime();
        String plaintext = encryptionText.getText();
        int[][] arrKey = {{Integer.parseInt(key1.getText()), Integer.parseInt(key2.getText())}
                , {Integer.parseInt(key3.getText()), Integer.parseInt(key4.getText())}};
        if (plaintext.isEmpty() && key1.getText().isEmpty() && key2.getText().isEmpty() && key3.getText().isEmpty() && key4.getText().isEmpty()) {
            decryptionText.setText("WRONG BROOOOOOO");
        } else {
            decryptionText.setText(cipher.Encryption(plaintext, arrKey));
        }
        Long ENDTIME = System.nanoTime();
        System.out.println("Time for Encryption Process: " + (ENDTIME - STARTTIME) / Math.pow(10, 9) + " second");

        ltShow1.setText("Time for Encryption Process: " + (ENDTIME - STARTTIME) / Math.pow(10, 9) + " second");
    }

    private void decryptText() {
        Long START_TIME = System.nanoTime();
        String ciphertext = decryptionText.getText();
        int[][] arrKey = {{Integer.parseInt(key1.getText()), Integer.parseInt(key2.getText())}
                , {Integer.parseInt(key3.getText()), Integer.parseInt(key4.getText())}};

       Matrix matrix=new Matrix();
       if(matrix.det(arrKey)==0){encryptionText.setText("Determenant is Zero");encryptionText.setBackground(Background.fill(Color.RED));}
       else{
        if ((ciphertext.isEmpty() && key1.getText().isEmpty() && key2.getText().isEmpty() && key3.getText().isEmpty() && key4.getText().isEmpty())) {
            encryptionText.setText("WRONG BROOOOOOO");
        } else {
            System.out.println("here");
            encryptionText.setText(cipher.Decryption(ciphertext, arrKey));
        }
        Long END_TIME = System.nanoTime();
        System.out.println("Time for Decryption Process: " + (END_TIME - START_TIME) / Math.pow(10, 9) + " second");
        ltShow2.setText("Time for Decryption Process: " + (END_TIME - START_TIME) / Math.pow(10, 9) + " second");
    }}

    enum Page {
        ENCRYPTION,
        DECRYPTION,
        ATTACK_BRUTE,
        ATTACK_KNOWN
    }
}
