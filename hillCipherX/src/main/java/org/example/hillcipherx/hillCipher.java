package org.example.hillcipherx;

import java.util.ArrayList;

public class hillCipher {

    public ArrayList<Integer> plaintextToArrayWithNumber(String plaintext) {
        plaintext = plaintext.toUpperCase();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < plaintext.length(); i++) {
            if (Character.isLetter(plaintext.charAt(i))) {
                arrayList.add( (plaintext.charAt(i) - 65));
            }
        }
        if (arrayList.size() % 2 == 1) {
            arrayList.add(0);
        }

        return arrayList;
    }

    public String arrayWithNumberToPlaintext(ArrayList<Integer> arrayList1) {

        ArrayList<Character> arrayList = new ArrayList<>();
        for (int i = 0; i < arrayList1.size(); i++) {
            int cc = arrayList1.get(i);
            char c = (char) (cc + 65);
            arrayList.add(c);
        }

        return arrayList.toString();
    }

    public ArrayList<Integer> toArraylistCipher(ArrayList<Integer> plaintext, int[][] key) {
        ArrayList<Integer> cipher = new ArrayList<>();
        for (int i = 0; i < plaintext.size(); i += 2) {
            cipher.add((plaintext.get(i) * key[0][0] + plaintext.get(i + 1) * key[0][1]) % 26);
            cipher.add((plaintext.get(i) * key[1][0] + plaintext.get(i + 1) * key[1][1]) % 26);
        }
        return cipher;
    }

    static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    static int modInverse(int A, int M) {
        if (gcd(A, M) > 1) {

            // modulo inverse does not exist
            return -1;
        }
        for (int X = 1; X < M; X++)
            if (((A % M) * (X % M)) % M == 1)
                return X;
        return 1;
    }

        public String Encryption (String plainText,int[][] key){
            return arrayToString(plainText,toArraylistCipher(plaintextToArrayWithNumber(plainText), key));
        }

        public String Decryption (String cipherText,int[][] key){
        Matrix matrix = new Matrix();
       int mInv=modInverse(matrix.det(key),26);

           int [][]matInv= matrix.addNum(matrix.multiNum(matrix.inverseMat(key),mInv),26,26);

            System.out.println( arrayWithNumberToPlaintext(toArraylistCipher(plaintextToArrayWithNumber(cipherText), matInv)));
         return    arrayToString(cipherText,toArraylistCipher(plaintextToArrayWithNumber(cipherText), matInv));
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

    public int modInverse26(int num) {
        int[][] arr = {{1, 1}
                , {3, 9}
                , {5, 21}
                , {7, 15}
                , {9, 3}
                , {11, 19}
                , {15, 7}
                , {17, 23}
                , {19, 11}
                , {21, 5}
                , {23, 17}
                , {25, 25}

        };
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0]  == num) {
                return arr[i][1];
            }
        }
        return 0;
    }
    }
