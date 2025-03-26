package org.example.cesarCipher;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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
    private final TextField textField7 = new TextField();
    private final TextField textField8 = new TextField();
    private final TextField textField9 = new TextField();
    private final TextField textFieldT = new TextField();
    private final Cipher cipher = new Cipher();
    Label ltShow1 = createLabel("");
    Label ltShow2 = createLabel("");
    Label ltShow3 = createLabel("");
    Label ltShow = createLabel("");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Encryption/Decryption UI");


        GridPane grid1 = new GridPane();
        GridPane grid2 = new GridPane();
        GridPane grid3 = new GridPane();
        GridPane grid4 = new GridPane();
        AnchorPane anchorPane=new AnchorPane();
        anchorPane.setScaleX(1000);
        anchorPane.setScaleY(1000);


        GridShow(grid1, grid2);
        GridShow(grid3, grid4);


        TabPane tabPane = new TabPane();
        BorderPane root = new BorderPane();
        Tab tab1 = new Tab("Encryption");
        Tab tab2 = new Tab("Decryption");
        Tab tab3 = new Tab("Attack");
        Tab tab4 = new Tab("Know-Attack");
        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);
        tabPane.getTabs().add(tab4);
        root.setCenter(tabPane);
        tab1.setClosable(false);
        tab2.setClosable(false);
        tab3.setClosable(false);
        tab4.setClosable(false);


        Label l1 = createLabel("Message");
        Label l2 = createLabel("Key");
        Label l3 = createLabel("CipherText");
        Label l4 = createLabel("CipherText");
        Label l5 = createLabel("key");
        Label l6 = createLabel("Message");



        Button btnEnc = new Button("ENCRYPTION");
        Button btnDec = new Button("DECRYPTION");
        Button btnATKbr = new Button("attackWithBruteForce");
        Button btnATKKnown = new Button("attackWithKnown");

        tabONCE(grid1, tab1, l1, l2, l3, btnEnc, textField1, textField2, textField3, ltShow1);

        tabONCE(grid2, tab2, l4, l5, l6, btnDec, textField4, textField5, textField6, ltShow2);
        tab3.setContent(grid3);
        textFieldT.setScaleX(2);
        textFieldT.setScaleY(2);
        btnATKbr.setScaleX(2);
        btnATKbr.setScaleY(2);
        grid3.add(textFieldT,0,0);
        grid3.add(btnATKbr,0,5);
        grid3.add(ltShow3,0,8);

        Label label1 = new Label("PLAINTEXT:");
        Label label2 = new Label("CIPHERTEXT:");
        HBox hbox = new HBox(15, label1, textField7, label2, textField8);
        hbox.setAlignment(Pos.CENTER);
        // Third row
        textField9.setPromptText("Result");

        // Main layout
        VBox vbox = new VBox(15, hbox, btnATKKnown, textField9,ltShow);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        tab4.setContent(vbox);
        btnEnc.setOnAction(event -> encryptText());
         btnDec.setOnAction(event -> decryptText());
        btnATKbr.setOnAction(event -> attackText());
         btnATKKnown.setOnAction(event -> attackWithKnown());



        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
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
    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", 18));
        label.setTextFill(Color.BLACK);
        return label;
    }
    private void encryptText() {
        Long STARTTIME = System.nanoTime();
        String plaintext = textField1.getText();
        String key = textField2.getText();
        if (plaintext.isEmpty() || key.isEmpty()) {
            textField3.setText("WRONG BROOOOOOO");
        } else {
            textField3.setText(cipher.Encryption(plaintext, Integer.parseInt(key)));
        }
        Long ENDTIME = System.nanoTime();
        System.out.println("Time for Encryption Process: " + (ENDTIME - STARTTIME) / Math.pow(10, 9) + " second");

        ltShow1.setText("Time for Encryption Process: " + (ENDTIME - STARTTIME) / Math.pow(10, 9) + " second");
    }
    private void decryptText() {
        Long START_TIME = System.nanoTime();
        String ciphertext = textField4.getText();
        String key = textField5.getText();
        if (ciphertext.isEmpty() || key.isEmpty()) {
            textField6.setText("WRONG BROOOOOOO");
        } else {
            textField6.setText(cipher.Decryption(ciphertext, Integer.parseInt(key)));
        }
        Long END_TIME = System.nanoTime();
        System.out.println("Time for Decryption Process: " + (END_TIME - START_TIME) / Math.pow(10, 9) + " second");
        ltShow2.setText("Time for Decryption Process: " + (END_TIME - START_TIME) / Math.pow(10, 9) + " second");
    }
    private void attackText() {
        Long STARTTIME = System.nanoTime();
        cipher.bruteForceAttack(textFieldT.getText());
        Long ENDTIME = System.nanoTime();
        ltShow3.setText("Time for Attack with Brute Force Process: " + (ENDTIME - STARTTIME) / Math.pow(10, 6) + " second");
    }
    public void attackWithKnown() {
        Long STARTTIME = System.nanoTime();
        String plaintext = textField7.getText();
        String cipherText = textField8.getText();
        if (plaintext.isEmpty() || cipherText.isEmpty()) {
            textField9.setText("WRONG BROOOOOOO");
        } else {
            textField9.setText(cipher.knownAttack(plaintext, cipherText));
        }
        Long ENDTIME = System.nanoTime();
        System.out.println("Time for Attack with Known Process: " + (ENDTIME - STARTTIME) / Math.pow(10, 6) + " second");
        ltShow.setText("Time for Attack with Known Process " + (ENDTIME - STARTTIME) / Math.pow(10, 6) + " second");

    }

    public static void main(String[] args) {
        launch(args);
    }
    enum Page {
        ENCRYPTION,
        DECRYPTION,
        ATTACK_BRUTE,
        ATTACK_KNOWN
    }
}