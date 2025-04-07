package com.example.descipher;

public class PTBlockX {
    private numberBaseConversion conv = new numberBaseConversion();
    PTBlockX(){}
    public  String convertHexToBinary(String input) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            builder.append(conv.hexToBinary(input.substring(i, i + 1)));
          //  System.out.println(conv.hexToBinary(input.substring(i, i + 1)));
        }
        return builder.toString();
    }
    public String IP(String input) {
      // System.out.println(input);
      // System.out.println(input.length());
        int[] iP = {58, 50, 42, 34, 26, 18, 10, 2,
                60, 52, 44, 36, 28, 20, 12, 4,
                62, 54, 46, 38, 30, 22, 14, 6,
                64, 56, 48, 40, 32, 24, 16, 8,
                57, 49, 41, 33, 25, 17, 9, 1,
                59, 51, 43, 35, 27, 19, 11, 3,
                61, 53, 45, 37, 29, 21, 13, 5,
                63, 55, 47, 39, 31, 23, 15, 7};
        StringBuilder builder = new StringBuilder();
        //divide it for 4 bit
        //why -1 bczOf i want index 0 and i cant get it by bc1 the least number is 1
        // minusOne() --> 0
        for (int i = 0; i < iP.length; i++) {
            builder.append(input.charAt(iP[i]-1));
          //  builder.append(binaryArray[(iP[i] - 1) / 4].charAt((iP[i] - 1) % 4));
        }
        return builder.toString();
    }
    public String[] L0R0(String input) {

        return new String[]{input.substring(0,input.length()/2),input.substring(input.length()/2)};
    }
    public String iterationWithNumOfRound(String[] LiRi, String[] Ki, int numOfRound, Process process) {
        String[][] keyLeftAndRight = new String[17][2];

        // Initialize L0 and R0
        keyLeftAndRight[0][0] = LiRi[0];
        keyLeftAndRight[0][1] = LiRi[1];

        if (process == Process.ENCRYPTION) {
            performEncryptionRounds(keyLeftAndRight, Ki);
        } else if (process == Process.DECRYPTION) {
            performDecryptionRounds(keyLeftAndRight, Ki);
        }

        String L0R0 = keyLeftAndRight[keyLeftAndRight.length - 1][1] +
                keyLeftAndRight[keyLeftAndRight.length - 1][0];

        return finalInverseP(L0R0);
    }
    private void performEncryptionRounds(String[][] keyLeftAndRight, String[] Ki) {
        for (int i = 1; i < keyLeftAndRight.length; i++) {
            keyLeftAndRight[i][0] = keyLeftAndRight[i - 1][1];
            keyLeftAndRight[i][1] = XOR(keyLeftAndRight[i - 1][0],
                    ManaglerFun(keyLeftAndRight[i - 1][1], Ki[i - 1]));
        }
    }
    private void performDecryptionRounds(String[][] keyLeftAndRight, String[] Ki) {
        for (int i = 1; i < keyLeftAndRight.length; i++) {
            keyLeftAndRight[i][0] = keyLeftAndRight[i - 1][1];
            keyLeftAndRight[i][1] = XOR(keyLeftAndRight[i - 1][0],
                    ManaglerFun(keyLeftAndRight[i - 1][1], Ki[16 - i]));
        }
    }
    public String ManaglerFun(String Ri, String Ki) {
        String ER0=ESelectioTable(Ri);
        String XOR=XOR(ER0,Ki);
        String sBox=S_Box(XOR);
        return P(sBox);
    }
    public String ESelectioTable(String Ri) {

        int[] E = {32, 1, 2, 3, 4, 5,
                4, 5, 6, 7, 8, 9,
                8, 9, 10, 11, 12, 13,
                12, 13, 14, 15, 16, 17,
                16, 17, 18, 19, 20, 21,
                20, 21, 22, 23, 24, 25,
                24, 25, 26, 27, 28, 29,
                28, 29, 30, 31, 32, 1};
        StringBuilder builder = new StringBuilder();


        for (int i = 0; i < E.length/6; i ++) {
            builder.append(Ri.charAt(E[i*6]-1) );
            builder.append(Ri.charAt(E[i*6+1]-1) );
            builder.append(Ri.charAt(E[i*6+2]-1) );
            builder.append(Ri.charAt(E[i*6+3]-1) );
            builder.append(Ri.charAt(E[i*6+4]-1) );
            builder.append(Ri.charAt(E[i * 6 + 5] - 1));
        }

        return builder.toString();
    }
    public String S_Box(String Ri) {    //S-box Table

        int[][][] sbox =
                {{{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                        {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                        {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                        {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}},

                        {{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                                {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                                {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                                {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}},

                        {{10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                                {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                                {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                                {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}},

                        {{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                                {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                                {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                                {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}},

                        {{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                                {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                                {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                                {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}},

                        {{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                                {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                                {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                                {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}},

                        {{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                                {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                                {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                                {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}},

                        {{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                                {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                                {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                                {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}}};
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < sbox.length; i++) {
            int row = Integer.parseInt(conv.binaryToDecimal( String.valueOf(Ri.charAt(i * 6)) + String.valueOf(Ri.charAt(i * 6 + 5))));
            int col = Integer.parseInt(conv.binaryToDecimal(Ri.substring(i * 6 + 1, i * 6 + 5)));
            //add؟؟؟؟؟؟؟؟؟؟؟؟؟؟؟
            builder.append(conv.hexToBinary(conv.decimalToHex(String.valueOf(sbox[i][row][col]))));
        }
        return builder.toString();
    }
    String P(String input) {
        int[] iP = {16, 7, 20, 21,
                29, 12, 28, 17,
                1, 15, 23, 26,
                5, 18, 31, 10,
                2, 8, 24, 14,
                32, 27, 3, 9,
                19, 13, 30, 6,
                22, 11, 4, 25};
        StringBuilder builder = new StringBuilder();
        //divide it for 4 bit
        //why -1 bczOf i want index 0 and i cant get it by bc1 the least number is 1
        // minusOne() --> 0
        for (int i = 0; i < iP.length; i++) {
            builder.append(input.charAt(iP[i]-1));
            //  builder.append(binaryArray[(iP[i] - 1) / 4].charAt((iP[i] - 1) % 4));
        }
        return builder.toString();
    }
    String finalInverseP(String binaryArray) {
        int[] iP = {40, 8, 48, 16, 56, 24, 64, 32,
                39, 7, 47, 15, 55, 23, 63, 31,
                38, 6, 46, 14, 54, 22, 62, 30,
                37, 5, 45, 13, 53, 21, 61, 29,
                36, 4, 44, 12, 52, 20, 60, 28,
                35, 3, 43, 11, 51, 19, 59, 27,
                34, 2, 42, 10, 50, 18, 58, 26,
                33, 1, 41, 9, 49, 17, 57, 25};

        StringBuilder builder = new StringBuilder();
        //divide it for 4 bit
        //why -1 bczOf i want index 0 and i cant get it by bc1 the least number is 1
        // minusOne() --> 0
        for (int i = 0; i < binaryArray.length(); i+=4) {

                builder.append(conv.binaryToHex( String.valueOf(binaryArray.charAt(iP[i]-1))+
                        String.valueOf(binaryArray.charAt(iP[i+1]-1))+
                        String.valueOf(binaryArray.charAt(iP[i+2]-1))+
                        String.valueOf(binaryArray.charAt(iP[i+3]-1))));
        }
        return builder.toString();
    }
    public enum Process {
        ENCRYPTION,
        DECRYPTION
    }
    public String XOR(String OP1, String OP2) {
        //   System.out.println("Xor 2 Strings "+OP1.length() +" "+ OP2.length());
        // Lengths of the given strings
        int aLen = OP1.length();
        int bLen = OP2.length();

        // Make both the strings of equal lengths
        // by inserting 0s in the beginning
        if (aLen > bLen) {
            OP1 = addZeros(OP2, aLen - bLen);
        } else if (bLen > aLen) {
            OP1 = addZeros(OP1, bLen - aLen);
        }

        // Updated length
        int len = Math.max(aLen, bLen);

        // To store the resultant XOR
        String res = "";

        for (int i = 0; i < len; i++) {
            if (OP1.charAt(i) == OP2.charAt(i))
                res += "0";
            else
                res += "1";
        }
        return res;
    }
    String addZeros(String str, int n) {
        for (int i = 0; i < n; i++) {
            str = "0" + str;
        }
        return str;
    }
}
