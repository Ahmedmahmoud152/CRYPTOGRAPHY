package org.example.monoalphabiticcipher;

import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class monoCipher {
    ArrayList<Pair<Character, Character>> pairs = new ArrayList<>();

    monoCipher() {
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

    public ArrayList<Character> characterArrayList() {
        ArrayList<Character> arrayList = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            arrayList.add((char) (65 + i));
        }
        return arrayList;
    }

    public ArrayList<Pair<Character, Character>> addToPairMatrix(ArrayList<Pair<Character, Character>> pair, ArrayList<Character> arrayList1, ArrayList<Character> arrayList2) {
        for (int i = 0; i < 26; i++) {
            pair.add(i, new Pair<>(arrayList1.get(i), arrayList2.get(i)));
        }
        return pair;
    }

    public ArrayList<Pair<Character, Character>> duplicate(ArrayList<Pair<Character, Character>> pairs) {
        for (int i = 0; i < 26; i++) {
            pairs.add(new Pair<>(Character.toLowerCase(pairs.get(i).getKey()), Character.toLowerCase(pairs.get(i).getValue())));
        }
        return pairs;
    }

    public String Encryption(String plainText, String Key) {
        Long STARTTIME = System.nanoTime();
        ArrayList<Pair<Character, Character>> pairs1 = new ArrayList<>();
        pairs1 = addToPairMatrix(pairs1, characterArrayList(), distinctList(arrayListKey(Key), characterArrayList()));
        pairs1 = duplicate(pairs1);
        StringBuilder cypherText = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if (!Character.isLetter(c)) cypherText.append(c);
            for (int j = 0; j < 52; j++) {
                if (c == pairs1.get(j).getKey()) cypherText.append(pairs1.get(j).getValue());
            }
        }
        Long ENDTIME = System.nanoTime();
        System.out.println("Time for Encryption Process: " + (ENDTIME - STARTTIME) / Math.pow(10, 6) + " second");
        return cypherText.toString();
    }

    public int intKey(ArrayList<Character> matC, ArrayList<Character> matAZ) {

        int i = 0;
        int k=0;

        while (i < matAZ.size()) {
            if (matAZ.get(i) != (matC.get(i))) {
                for (int j = 0; j <= i; j++) {
                    if(i>=1+k){ k++;}
                    Character c = matC.get(i);
                    matAZ.remove(c);
                    matC.remove(c);
                }
                i = 0;
            } else {
                i++;
            }
        }
        return 26 - matC.size()+k;
    }

    public void EncryptionWithFileKey(String plainText) {
        Long STARTTIME = System.nanoTime();
        String Key = readFile("D:\\Life\\SpringBoot\\monoAlphabiticCipher\\src\\main\\java\\org\\example\\monoalphabiticcipher\\input.txt");
        ArrayList<Pair<Character, Character>> pairs1 = new ArrayList<>();
        pairs1 = addToPairMatrix(pairs1, characterArrayList(), distinctList(arrayListKey(Key), characterArrayList()));
        pairs1 = duplicate(pairs1);
        StringBuilder cypherText = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if ((int) c <= 57 || (int) c >= 123) cypherText.append(c);
            for (int j = 0; j < 52; j++) {
                if (c == pairs1.get(j).getKey()) cypherText.append(pairs1.get(j).getValue());
            }
        }
        Long ENDTIME = System.nanoTime();
        System.out.println("Time for EncryptionWithFileKey Process: " + (ENDTIME - STARTTIME) / Math.pow(10, 6) + " second");
        writeFile(cypherText.toString(), "D:\\Life\\SpringBoot\\monoAlphabiticCipher\\src\\main\\java\\org\\example\\monoalphabiticcipher\\output.txt");
    }

    public String readFile(String path) {
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString().trim().toUpperCase();
    }

    public void writeFile(String content, String path) {
        try {
            Files.write(Paths.get(path), content.getBytes());
            System.out.println("File written successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String Decryption(String cipherText, String Key) {
        Long STARTTIME = System.nanoTime();
        ArrayList<Pair<Character, Character>> pairs1 = new ArrayList<>();
        pairs1 = addToPairMatrix(pairs1, characterArrayList(), distinctList(arrayListKey(Key), characterArrayList()));
        pairs1 = duplicate(pairs1);
        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);
            if (!Character.isLetter(c)) plainText.append(c);
            for (int j = 0; j < 52; j++) {
                if (c == pairs1.get(j).getValue()) plainText.append(pairs1.get(j).getKey());
            }
        }
        Long ENDTIME = System.nanoTime();
        System.out.println("Time for Decryption Process: " + (ENDTIME - STARTTIME) / Math.pow(10, 6) + " second");
        return plainText.toString();
    }

    public String Attack( ArrayList<Character> matAZ) {
        String msg = readFile("D:\\Life\\SpringBoot\\monoAlphabiticCipher\\src\\main\\java\\org\\example\\monoalphabiticcipher\\input.txt");
        ArrayList<Character> matC =arrayListKey(msg);
        ArrayList<Character> matCA = (ArrayList<Character>) matC.clone();
        int kSz = intKey(matCA, matAZ);
        StringBuilder str=new StringBuilder("");
        for (int i = 0; i < kSz; i++) {
            str.append(matC.get(i));
        }
        writeFile(str.toString(),"D:\\Life\\SpringBoot\\monoAlphabiticCipher\\src\\main\\java\\org\\example\\monoalphabiticcipher\\output.txt");
        return str.toString();

    }
}
