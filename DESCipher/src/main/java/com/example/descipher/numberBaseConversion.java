package com.example.descipher;

public class numberBaseConversion {
    // Convert Binary (Base 2) to Decimal (Base 10)
    public  String binaryToDecimal(String binary) {
        return String.valueOf(Integer.parseInt(binary, 2));
    }

    // Convert Decimal (Base 10) to Binary (Base 2)
    public  String decimalToBinary(String decimal) {
        return Integer.toBinaryString(Integer.parseInt(decimal));
    }

    // Convert Decimal (Base 10) to Hexadecimal (Base 16)
    public  String decimalToHex(String decimal) {
        return Integer.toHexString(Integer.parseInt(decimal)).toUpperCase();
    }

    // Convert Hexadecimal (Base 16) to Decimal (Base 10)
    public  String hexToDecimal(String hex) {
        return String.valueOf(Integer.parseInt(hex, 16));
    }

    // Convert Binary (Base 2) to Hexadecimal (Base 16)
    public  String binaryToHex(String binary) {
        int decimal = Integer.parseInt(binary, 2);
        return Integer.toHexString(decimal).toUpperCase();
    }

    // Convert Hexadecimal (Base 16) to Binary (Base 2)
    public String hexToBinary(String hex) {
        int decimal = Integer.parseInt(hex, 16);
        return String.format("%4s", Integer.toBinaryString(decimal)).replace(' ', '0');
    }


    // Convert from Any Base to Any Base (Generic)
    public  String convertBase(String number, int fromBase, int toBase) {
        int decimal = Integer.parseInt(number, fromBase);
        return Integer.toString(decimal, toBase).toUpperCase();
    }
    public  String textToHex(String text) {
        StringBuilder hexString = new StringBuilder();
        for (char ch : text.toCharArray()) {
            hexString.append(String.format("%02X", (int) ch));
        }
        return hexString.toString();
    }
    public  String hexToText(String hex) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            String hexChar = hex.substring(i, i + 2); // Get two hex digits
            int decimal = Integer.parseInt(hexChar, 16); // Convert to decimal
            text.append((char) decimal); // Convert decimal to character
        }
        return text.toString();
    }
}
