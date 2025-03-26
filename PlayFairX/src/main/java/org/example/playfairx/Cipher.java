package org.example.playfairx;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cipher {
    char[][] chars = new char[5][5];

    Cipher() {
    }

    public ArrayList<Character> arrayListKey(String keyW) {
        ArrayList<Character> uniqueKey = new ArrayList<>();
        for (int i = 0; i < keyW.length(); i++) {
            switch (keyW.charAt(i)) {
                case ' ' -> {
                }
                default -> uniqueKey.add(keyW.charAt(i));
            }

        }
        return uniqueKey;
    }

    public ArrayList<Character> unique(ArrayList<Character> arrayListKey) {
        LinkedHashSet<Character> uniqueChars = new LinkedHashSet<>(arrayListKey);
        return new ArrayList<>(uniqueChars);
    }

    public ArrayList<Character> distinctList(ArrayList<Character> List1, ArrayList<Character> List2) {
        List1.addAll(List2);
        return unique(List1);
    }

    public char[][] characterMatrix(ArrayList<Character> List1) {
        char[][] chara = new char[5][5];
        int a = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                chara[i][j] = List1.get(a);
                a++;
            }
        }
        return chara;
    }

    public ArrayList<Character> characterArrayList() {
        ArrayList<Character> arrayList = new ArrayList<>();
        int a = 65;
        for (int i = 0; i < 25; i++) {
            if ((char) a == 'J') {
                arrayList.add((char) ++a);
            } else {
                arrayList.add((char) a);
            }
            a++;
        }
        return arrayList;
    }

    public ArrayList<Pair<Integer, Integer>> pairIndex(char[][] matrix, String str) {
        ArrayList<Pair<Integer, Integer>> pairs = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            for (int j = 0; j < matrix.length; j++) {
                for (int k = 0; k < matrix[0].length; k++) {
                    if (c == matrix[j][k]) {
                        pairs.add(new Pair<>(j, k));
                        break;
                    }
                }
            }
        }
        //System.out.println("The size is"+pairs.size());
        return pairs;

    }

    public ArrayList<Pair<Integer, Integer>> Shift(ArrayList<Pair<Integer, Integer>> pairs, Sign sign) {
        if (sign == Sign.INCREMENTAL) {

            for (int i = 0; i < pairs.size(); i += 2) {
                //System.out.println("shigt right");
                //ShiftRight
                // System.out.println(pairs.get(i).getKey()+" "+pairs.get(i + 1).getKey());
                if (pairs.get(i).getKey().equals(pairs.get(i + 1).getKey())) {

                    for (int j = 0; j < 2; j++) {
                        if (pairs.get(i + j).getValue() == 4) {
                            pairs.set(i + j, new Pair<>(pairs.get(i + j).getKey(), 0));
                        } else {
                            pairs.set(i + j, new Pair<>(pairs.get(i + j).getKey(), pairs.get(i + j).getValue() + 1));
                        }
                    }
                }
                //ShiftDown
                else if (pairs.get(i).getValue().equals(pairs.get(i + 1).getValue())) {
                    //  System.out.println("ShiftDown");
                    for (int j = 0; j < 2; j++) {
                        if (pairs.get(i + j).getKey() == 4) {
                            pairs.set(i + j, new Pair<>(0, pairs.get(i + j).getValue()));
                        } else {
                            pairs.set(i + j, new Pair<>(pairs.get(i + j).getKey() + 1, pairs.get(i + j).getValue()));
                        }
                    }

                }
                //Intersection
                else {
                    //  System.out.println("Intersection");
                    Pair<Integer, Integer> pair1 = new Pair<>(pairs.get(i).getKey(), pairs.get(i + 1).getValue());
                    Pair<Integer, Integer> pair2 = new Pair<>(pairs.get(i + 1).getKey(), pairs.get(i).getValue());
                    pairs.set(i, pair1);
                    pairs.set(i + 1, pair2);
                }
            }
        }
        if (sign == Sign.DECREMENTAL) {
            for (int i = 0; i < pairs.size(); i += 2) {
                //ShiftLeft
                if (pairs.get(i).getKey().equals(pairs.get(i + 1).getKey())) {

                    for (int j = 0; j < 2; j++) {
                        if (pairs.get(i + j).getValue() == 0) {
                            pairs.set(i + j, new Pair<>(pairs.get(i + j).getKey(), 4));
                        } else {
                            pairs.set(i + j, new Pair<>(pairs.get(i + j).getKey(), pairs.get(i + j).getValue() - 1));
                        }
                    }
                }
                //ShiftTop
                else if (pairs.get(i).getValue().equals(pairs.get(i + 1).getValue())) {
                    for (int j = 0; j < 2; j++) {
                        if (pairs.get(i + j).getKey() == 0) {
                            pairs.set(i + j, new Pair<>(4, pairs.get(i + j).getValue()));
                        } else {
                            pairs.set(i + j, new Pair<>(pairs.get(i + j).getKey() - 1, pairs.get(i + j).getValue()));
                        }
                    }
                }
                //Intersection
                else {
                    Pair<Integer, Integer> pair1 = new Pair<>(pairs.get(i).getKey(), pairs.get(i + 1).getValue());
                    Pair<Integer, Integer> pair2 = new Pair<>(pairs.get(i + 1).getKey(), pairs.get(i).getValue());
                    pairs.set(i, pair1);
                    pairs.set(i + 1, pair2);
                }
            }
        }
        return pairs;
    }

    public int intKey(ArrayList<Character> matC, ArrayList<Character> matAZ) {

        int i = 0;
        while (i < matAZ.size()) {
            if (matAZ.get(i) != (matC.get(i))) {
                for (int j = 0; j <= i; j++) {
                    Character c = matC.get(i);
                    matAZ.remove(c);
                    matC.remove(c);
                }
                i = 0;
            } else {
                i++;
            }
        }
        return 26 - matAZ.size() + 1;
    }

    public ArrayList<Character> twDiToArrayList(char[][] cipherMatrix) {
        ArrayList<Character> charArrLst = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                charArrLst.add(cipherMatrix[i][j]);
            }
        }
        return charArrLst;
    }

    public String pre(String str) {
        //plianText Upper
        str = str.toUpperCase();
        //remove(Special Character)
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 65 && str.charAt(i) <= 91) {
                builder.append(str.charAt(i));
            }
        }
        str = builder.toString();
        //Odd((repeatCharacter(Odd)))
        if (str.length() % 2 == 1) {
            str = str + "X";
        }
        StringBuilder builders = new StringBuilder();
        for (int i = 0; i < str.length(); i += 2) {
            if (i + 1 == str.length()) {
                builders.append(str.charAt(i));
                builders.append("X");
                break;
            }
            if (str.charAt(i) == str.charAt(i + 1)) {
                builders.append(str.charAt(i));
                builders.append("X");
                i--;
            } else {
                builders.append(str.charAt(i));
                builders.append(str.charAt(i + 1));
            }
        }
        str = builders.toString();
        if (str.length() % 2 == 1) {
            str = str + "X";
        }
        return str;
    }

    public String afterDec(String str) {
        StringBuilder builder = new StringBuilder();
        if (str.charAt(str.length() - 1) == 'X') {
            str.substring(0, str.length() - 1);
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != 'X') {
                builder.append(str.charAt(i));
            }
        }

        return builder.toString();
    }

    public String finalMethodMerge(String plainText, String cipherText) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        int j = 0;
        while (i < plainText.length() || j < cipherText.length()) {

            if (i >= plainText.length()) {
                for (int k = j; k < cipherText.length(); k++) {
                    builder.append(cipherText.charAt(k));
                }
                break;
            } else if (j >= cipherText.length()) {
                for (int k = i; k < plainText.length(); k++) {
                    builder.append(plainText.charAt(k));
                }
                break;
            } else if (Character.isLetter(plainText.charAt(i))) {
                builder.append(cipherText.charAt(j));
                i++;
                j++;
            } else if (!Character.isLetter(plainText.charAt(i))) {
                builder.append(plainText.charAt(i));
                i++;
            }
        }

        return builder.toString();
    }

    public String cipherText(char[][] matrix, ArrayList<Pair<Integer, Integer>> pairs) {
        StringBuilder str = new StringBuilder();
        for (Pair<Integer, Integer> pair : pairs) {
            str.append(matrix[pair.getKey()][pair.getValue()]);
        }

        return str.toString();
    }

    public String Encryption(String plainText, String Key) {
        String newPlainText = pre(plainText);
        this.chars = characterMatrix(distinctList(arrayListKey(Key), characterArrayList()));
        return finalMethodMerge(plainText, cipherText(this.chars, Shift(pairIndex(this.chars, newPlainText), Sign.INCREMENTAL)));
    }

    public String Decryption(String cipherText, String Key) {
        String newCipherText = pre(cipherText);
        this.chars = characterMatrix(distinctList(arrayListKey(Key), characterArrayList()));
        return afterDec(finalMethodMerge(cipherText, cipherText(this.chars, Shift(pairIndex(this.chars, newCipherText), Sign.DECREMENTAL))));
    }

    public String Attack(char[][] cipherMatrix, ArrayList<Character> matAZ) {
        ArrayList<Character> matC = twDiToArrayList(cipherMatrix);
        ArrayList<Character> matCA = (ArrayList<Character>) matC.clone();
        int kSz = intKey(matCA, matAZ);
        return IntStream.range(0, kSz).mapToObj(i -> String.valueOf(matC.get(i))).collect(Collectors.joining());

    }

    enum Sign {
        INCREMENTAL,
        DECREMENTAL
    }
}