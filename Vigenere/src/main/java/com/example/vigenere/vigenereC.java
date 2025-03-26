package com.example.vigenere;


import java.util.ArrayList;


public class vigenereC {

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

        return arrayToString(plainText, listResult);
    }

    public String Decryption(String cipherText, String key) {
        ArrayList<Integer> listCipherText = ConvertStringToArrayNumber(cipherText);
        ArrayList<Integer> listKey = ConvertStringToArrayNumber(key);
        ArrayList<Integer> listResult = decrypt(listCipherText, listKey);

        return arrayToString(cipherText, listResult);
    }
}