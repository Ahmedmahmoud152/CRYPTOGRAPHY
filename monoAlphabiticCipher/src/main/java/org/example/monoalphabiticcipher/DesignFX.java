package org.example.monoalphabiticcipher;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DesignFX extends Application {
    private final TextField textField1 = new TextField();
    private final TextField textField2 = new TextField();
    private final TextField textField3 = new TextField();
    private final TextField textField4 = new TextField();
    private final TextField textField5 = new TextField();
    private final TextField textField6 = new TextField();
    TextField []arr=new TextField[26];

    Label ltShow1 = createLabel("");
    Label ltShow2 = createLabel("");
    private final monoCipher cipher = new monoCipher();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Encryption/Decryption UI");



        GridPane grid1 = new GridPane();
        GridPane grid2 = new GridPane();
        GridPane gridPane = new GridPane();

        // Create and add 26 TextFields to the GridPane
        for (int i = 0; i < 26; i++) {
            TextField textField = new TextField();
            int row = i / 5; // 5 columns per row
            int col = i % 5;
            textField.setScaleX(1);
            textField.setScaleY(1);
            gridPane.add(textField, col, row);
        }



        TabPane tabPane = new TabPane();
        BorderPane root = new BorderPane();
        root.setCenter(tabPane);

        Tab tab1 = new Tab("Encryption");
        Tab tab2 = new Tab("Decryption");
        Tab tab3 = new Tab("Attack");

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);
         tab3.setContent(gridPane);
        GridShow(grid1, grid2);

        Label l1 = createLabel("PlainText");
        Label l2 = createLabel("Key");
        Label l3 = createLabel("Encryption");
        Label l4 = createLabel("Decryption");
        Label l5 = createLabel("Key");
        Label l6 = createLabel("CipherText");

        Button btnEnc = new Button("ENC");
        Button btnDec = new Button("DEC");

        Button btnEncWithFile = new Button("EncryptionWithFile");


        tabONCE(grid1, tab1, l1, l2, l3, btnEnc, textField1, textField2, textField3, ltShow1);
        tabONCE(grid2, tab2, l4, l5, l6, btnDec, textField4, textField5, textField6, ltShow2);

        btnEnc.setOnAction(event -> encryptText());
        btnDec.setOnAction(event -> decryptText());
        btnEncWithFile.setOnAction(event -> encryptWithFile());


        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", 18));
        label.setTextFill(Color.BLACK);
        return label;
    }
    private void tabONCE(GridPane grid1, Tab tab1, Label l1, Label l2, Label l3, Button btnEnc, TextField textField1, TextField textField2, TextField textField3, Label ltShow1) {
        tab1.setContent(grid1);
        grid1.add(l1, 0, 0);
        grid1.add(l2, 0, 6);
        grid1.add(l3, 0, 12);
        grid1.add(textField1, 8, 0);
        grid1.add(textField2, 8, 6);
        grid1.add(textField3, 8, 12);
        grid1.add(btnEnc, 8, 16);
        grid1.add(ltShow1, 8, 20);
        textField1.setScaleX(2);
        textField2.setScaleX(2);
        textField3.setScaleX(2);
        btnEnc.setScaleX(2);
        textField1.setScaleY(2);
        textField2.setScaleY(2);
        textField3.setScaleY(2);
        btnEnc.setScaleY(2);
    }
    private void GridShow(GridPane grid1, GridPane grid2) {
        grid1.setAlignment(Pos.CENTER);
        grid1.setHgap(10);
        grid1.setVgap(10);
        grid1.setPadding(new Insets(25, 25, 25, 25));

        grid2.setAlignment(Pos.CENTER);
        grid2.setHgap(10);
        grid2.setVgap(10);
        grid2.setPadding(new Insets(25, 25, 25, 25));
    }
    private void encryptText() {
        Long STARTTIME = System.nanoTime();
        String plaintext = textField1.getText();
        String key = textField2.getText();
        if (plaintext.isEmpty() || key.isEmpty()) {
            textField3.setText("WRONG BROOOOOOO");
        } else {
            textField3.setText(cipher.Encryption(plaintext, key));
        }
        Long ENDTIME = System.nanoTime();
        ltShow1.setText("Time for Encryption Process: " + (ENDTIME - STARTTIME) / Math.pow(10, 6) + " second");
    }

    private void decryptText() {
        Long STARTTIME = System.nanoTime();
        String ciphertext = textField4.getText();
        String key = textField5.getText();
        if (ciphertext.isEmpty() || key.isEmpty()) {
            textField6.setText("WRONG BROOOOOOO");
        } else {
            textField6.setText(cipher.Decryption(ciphertext, key));
        }
        Long ENDTIME = System.nanoTime();
        ltShow2.setText("Time for Decryption Process: " + (ENDTIME - STARTTIME) / Math.pow(10, 6) + " second");
    }

    private void encryptWithFile() {
        String plaintext = textField1.getText();
        if (plaintext.isEmpty()) {
            textField4.setText("WRONG BROOOOOOO");
        } else {
            cipher.EncryptionWithFileKey(plaintext);
        }
    }
    private void AttackMono() {

    }


    public static void main(String[] args) {
        launch(args);
       //monoCipher monoCipher=new monoCipher();
       //String i=monoCipher.Attack( monoCipher.characterArrayList());


    }
}