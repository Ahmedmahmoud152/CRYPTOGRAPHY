package com.example.descipher;

public class KeyGeneration {

    KeyGeneration() {
    }
    public static String[] hexToBinaryArray(String hex) {
        String[] binaryArray = new String[hex.length()];

        for (int i = 0; i < hex.length(); i++) {
            char hexChar = hex.charAt(i);
            binaryArray[i] = String.format("%4s", Integer.toBinaryString(Integer.parseInt(String.valueOf(hexChar), 16)))
                    .replace(' ', '0'); // Ensure 4-bit format
        }

        return binaryArray;
    }

    public String PC1(String[] binaryArray) {
        int[] pc1Array = {57, 49, 41, 33, 25, 17, 9,
                1, 58, 50, 42, 34, 26, 18,
                10, 2, 59, 51, 43, 35, 27,
                19, 11, 3, 60, 52, 44, 36,
                63, 55, 47, 39, 31, 23, 15,
                7, 62, 54, 46, 38, 30, 22,
                14, 6, 61, 53, 45, 37, 29,
                21, 13, 5, 28, 20, 12, 4};

        StringBuilder builder = new StringBuilder();
        //divide it for 4 bit
        //why -1 bczOf i want index 0 and i cant get it by bc1 the least number is 1
        // minusOne() --> 0
        for (int i = 0; i < pc1Array.length; i++) {
            builder.append(binaryArray[(pc1Array[i] - 1) / 4].charAt((pc1Array[i] - 1) % 4));
        }
        return builder.toString();
    }

    public String[] DivideToL0R0(String binaryKey) {
        String L0 = binaryKey.substring(0, binaryKey.length() / 2);
        String R0 = binaryKey.substring(binaryKey.length() / 2);
        return new String[]{L0, R0};
    }

    public String[][] holdKeyLeftAndRight(String[] binaryKey) {
        String[][] keyLeftAndRight = new String[17][2];
        //initial L0 AND R0
        keyLeftAndRight[0][0] = binaryKey[0];
        keyLeftAndRight[0][1] = binaryKey[1];

        int[] leftSheft = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
        for (int i = 1; i < keyLeftAndRight.length; i++) {
            keyLeftAndRight[i][0] = numOfLeftShift(keyLeftAndRight[i - 1][0], leftSheft[i - 1]);
            keyLeftAndRight[i][1] = numOfLeftShift(keyLeftAndRight[i - 1][1], leftSheft[i - 1]);
        }
        return keyLeftAndRight;
    }

    public String numOfLeftShift(String SubKey, int shift) {
        StringBuilder newSubKey = new StringBuilder();
        for (int i = 0; i < SubKey.length(); i++) {
            newSubKey.append(SubKey.charAt((i + shift) % SubKey.length()));
        }

        return newSubKey.toString();
    }

    public String[] concatenate(String[][] binaryArray) {
        String[] concatenatedArray = new String[binaryArray.length - 1];
        for (int i = 1; i < binaryArray.length; i++) {
            concatenatedArray[i - 1] = binaryArray[i][0] + binaryArray[i][1];
        }
        return concatenatedArray;
    }

    public String[] PC2(String[] binaryArray) {
        int[] pc2Array = {
                14, 17, 11, 24, 1, 5, 3, 28,
                15, 6, 21, 10, 23, 19, 12, 4,
                26, 8, 16, 7, 27, 20, 13, 2,
                41, 52, 31, 37, 47, 55, 30, 40,
                51, 45, 33, 48, 44, 49, 39, 56,
                34, 53, 46, 42, 50, 36, 29, 32
        };


        String[] builder = new String[binaryArray.length];
        StringBuilder stringBuilder = new StringBuilder();
        for (int k = 0; k < binaryArray.length; k++) {
            for (int i = 0; i < pc2Array.length; i++) {
                stringBuilder.append(binaryArray[k].charAt(pc2Array[i] - 1));
            }
            builder[k] = stringBuilder.toString();
            stringBuilder = new StringBuilder();
        }
        return builder;
    }
}