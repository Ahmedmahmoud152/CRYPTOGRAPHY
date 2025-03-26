package com.example.onetimepad;

import java.util.ArrayList;
import java.util.List;

public class oneTimeCipher {
    public ArrayList<Integer> ConvertStringToArrayNumber(String plainText) {
        plainText = plainText.toUpperCase();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < plainText.length(); i++) {
            if (Character.isLetter(plainText.charAt(i))) {
                char letter = plainText.charAt(i);
                list.add((letter) - 65);
            }
        }
        return list;
    }

    public String withSpecailAnyThing(String plainText, ArrayList<Integer> list2) {
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

    public ArrayList<Integer> encrypt(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> list3 = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            list3.add((list1.get(i) + list2.get(i % list2.size())) % 26);
        }
        return list3;
    }

    public ArrayList<Integer> decrypt(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> list3 = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            list3.add(((list1.get(i) - list2.get(i % list2.size())) + 26) % 26);
        }

        return list3;
    }

    public String Encryption(String plainText, String key) {
        ArrayList<Integer> listPlainText = ConvertStringToArrayNumber(plainText);
        ArrayList<Integer> listKey = ConvertStringToArrayNumber(key);
        ArrayList<Integer> listResult = encrypt(listPlainText, listKey);
        return withSpecailAnyThing(plainText, listResult);
    }

    public String Decryption(String cipherText, String key) {
        ArrayList<Integer> listCipherText = ConvertStringToArrayNumber(cipherText);
        ArrayList<Integer> listKey = ConvertStringToArrayNumber(key);
        ArrayList<Integer> listResult = decrypt(listCipherText, listKey);
        return withSpecailAnyThing(cipherText, listResult);
    }
    static String getAlphaNumericString(int n)
    {

        // choose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder randomKey = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            //  AlphaNumericString variable length
            int index
                    = (AlphaNumericString.length()
                    * (randomoly(AlphaNumericString.charAt(i%26),13,15))%26);

            // add Character one by one in end of sb
            randomKey.append(AlphaNumericString
                    .charAt(index));
        }

        return randomKey.toString();
    }

    public static int randomoly(int x, int a,int b){
        return (a*x+b)%26;

    }
    public static String generateRandomKeys(int seed, int a, int b, int length) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvwxyz";
        StringBuilder randomKey = new StringBuilder();
        int xi = seed;

        for (int i = 0; i < length; i++) {
            xi = ((a * xi + b) % 52);

            randomKey.append(AlphaNumericString.charAt(xi));
        }

        return randomKey.toString();
    }
}