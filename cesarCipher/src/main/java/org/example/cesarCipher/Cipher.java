package org.example.cesarCipher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cipher {
    Cipher() {
    }

    public int[] ConvertStringToArrayNumber(String plainText) {
        int len = plainText.length();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = plainText.charAt(i);
        }
        return arr;
    }

    public String Encryption(String plainText, int key) {
        int[] arr;
        arr = ConvertStringToArrayNumber(plainText);
        for (int i = 0; i < plainText.length(); i++) {
            if (arr[i] <= 57 || arr[i] >= 123) continue;
            if (arr[i] >= 97) arr[i] = (((arr[i] - 97) + key) % 26) + 97;
            if (arr[i] >= 65 && arr[i] <= 90) arr[i] = (((arr[i] - 65) + key) % 26) + 65;
        }
        return arrayToString(arr);
    }

    public String Decryption(String plainText, int key) {
        int[] arr = ConvertStringToArrayNumber(plainText);

        for (int i = 0; i < plainText.length(); i++) {

            if (arr[i] <= 57 || arr[i] >= 123) continue;
            if (((arr[i] - 97) - key) <= 0 && arr[i] >= 97) {
                arr[i] = (((arr[i] - 97) - key + 26) % 26) + 97;
                continue;
            }
            if (((arr[i] - 97) - key) > 0 && arr[i] >= 97) {
                arr[i] = (((arr[i] - 97) - key) % 26) + 97;
                continue;
            }
            if (((arr[i] - 65) - key) <= 0 && arr[i] >= 65 && arr[i] <= 90) {
                arr[i] = (((arr[i] - 65) - key + 26) % 26) + 65;
                continue;
            }
            if (((arr[i] - 65) - key) > 0 && arr[i] >= 65 && arr[i] <= 90) {
                arr[i] = (((arr[i] - 65) - key) % 26) + 65;
            }
        }


        return arrayToString(arr);
    }

    public void bruteForceAttack(String Mesg) {
        if (!Mesg.equals(null) && !Objects.equals(Mesg, "")) {

            String result = IntStream.range(0, 26)
                    .mapToObj(k -> "Message : " + Decryption(Mesg, k) + " with key " + k)
                    .collect(Collectors.joining("\n"));
            writeFile(result, "D:\\Life\\SpringBoot\\demo5\\src\\main\\java\\org\\example\\demo5\\output.txt");
        } else {
            String msg = readFile("D:\\Life\\SpringBoot\\demo5\\src\\main\\java\\org\\example\\demo5\\input.txt");
            String result = IntStream.range(0, 26)
                    .mapToObj(k -> "Message : " + Decryption(msg, k) + " with key " + k)
                    .collect(Collectors.joining("\n"));
            writeFile(result, "D:\\Life\\SpringBoot\\demo5\\src\\main\\java\\org\\example\\demo5\\output.txt");
        }
    }

    public String knownAttack(String plaintext, String cipherText) {
        for (int i = 0; i < 26; i++) {
            if (Encryption(plaintext, i).equals(cipherText)) {
                return i + "";
            }
        }
        return -1 + "";
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
        return content.toString().trim();
    }

    public void writeFile(String content, String path) {
        try {
            Files.write(Paths.get(path), content.getBytes());
            System.out.println("File written successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String arrayToString(int[] arr) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            text.append((char) arr[i]);
        }

        return text.toString();

    }

    public String removeSpace(String plainText) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if (!Character.isWhitespace(c)) {
                s.append(c);
            }
        }
        return s.toString();
    }

    public int StringToNum(String plainText) {
        int key = 0;
        int[] arr = ConvertStringToArrayNumber(plainText);
        for (int j : arr) {
            key = key * 10 + j;
        }
        return key;
    }
}
