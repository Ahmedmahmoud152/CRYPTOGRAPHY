package com.example.descipher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DesCipher {
    KeyGeneration k = new KeyGeneration();
    PTBlockX blockX = new PTBlockX();
    numberBaseConversion conv =new numberBaseConversion();
    DesCipher(){}
    String[] keyGeneration(String input) {
        String[] array = k.hexToBinaryArray(input);
        String s = k.PC1(array);
        String[] Key = k.DivideToL0R0(s);
        String[][] keyLeftAndRight = k.holdKeyLeftAndRight(Key);
        String[] concatenate = k.concatenate(keyLeftAndRight);
        return k.PC2(concatenate);
    }
    String encrypt(String plainText , String key) {
            String array = blockX.convertHexToBinary(plainText);
            String s = blockX.IP(array);
            String[] Key = blockX.L0R0(s);
        return  blockX.iterationWithNumOfRound(Key,keyGeneration(key),16, PTBlockX.Process.ENCRYPTION);
    }
    String decrypt(String cipherText , String key) {
        String array = blockX.convertHexToBinary(cipherText);
        String s = blockX.IP(array);
        String[] Key = blockX.L0R0(s);
        return blockX.iterationWithNumOfRound(Key,keyGeneration(key),16, PTBlockX.Process.DECRYPTION);
    }
    public void writeFile(String content, String path) {
        try {
            Files.write(Paths.get(path), content.getBytes());
            System.out.println("File written successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String arrayToString(String plainText, ArrayList<Integer> list2) {
        StringBuilder text = new StringBuilder();
        int i = 0;
        int j = 0;
        while (i < plainText.length() || j < list2.size()) {
            if (i >= plainText.length()) {
                for (int k = j; k < list2.size(); k++) {
                    text.append(list2.get(k));
                }
                break;

            } else if (j >= list2.size()) {
                for (int k = i; k < plainText.length(); k++) {
                    text.append(plainText.charAt(k));
                }
                break;

            } else if (Character.isLetter(plainText.charAt(i))) {

                text.append(plainText.charAt(i)<=123 &&plainText.charAt(i) >= 97 ? (char) (list2.get(j) + 97) : (char) (list2.get(j) + 65));
                i++;
                j++;
            } else if (!Character.isLetter(plainText.charAt(i))) {
                text.append(plainText.charAt(i));
                i++;

            }

        }

        return text.toString();

    }

    public String addX(String x,int repeat){
        for (int i = 0; i < repeat; i++) {
            x=x+"X";
        }
        return  x;
    }

    public String encrypts(String plainText,String key){

        if(plainText.length()%8!=0){
            plainText=addX(plainText,8-(plainText.length()%8));
        }
        System.out.println(plainText.length());
        System.out.println(plainText);

        int len =plainText.length();

        String hold="";

        for (int i = 0; i <len/8 ; i++) {
            String textToHex ="";
            for (int j = 0; j < 8; j++) {
                textToHex=textToHex+conv.textToHex(String.valueOf(plainText.charAt(j+i*8)));

            }
            String array = blockX.convertHexToBinary(textToHex);
            String s = blockX.IP(array);
            String[] Key = blockX.L0R0(s);



            hold=hold+conv.hexToText(blockX.iterationWithNumOfRound(Key,keyGeneration(key),16, PTBlockX.Process.ENCRYPTION));
        }


        return hold;
    }

    String decrypts(String cipherText , String key) {
        if(cipherText.length()%8!=0){
            cipherText=addX(cipherText,8-(cipherText.length()%8));
        }

        int len =cipherText.length();

        String hold="";

        for (int i = 0; i <len/8 ; i++) {
            String textToHex ="";
            for (int j = 0; j < 8; j++) {
                textToHex=textToHex+conv.textToHex(String.valueOf(cipherText.charAt(j)));

            }
             textToHex=conv.textToHex(cipherText.substring(i*8,i*8+8));
            String array = blockX.convertHexToBinary(textToHex);
            String s = blockX.IP(array);
            String[] Key = blockX.L0R0(s);
            hold=hold+ conv.hexToText(blockX.iterationWithNumOfRound(Key,keyGeneration(key),16, PTBlockX.Process.DECRYPTION));
        }

        return hold;
    }
}
