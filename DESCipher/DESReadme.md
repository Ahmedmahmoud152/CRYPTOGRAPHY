[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/Uo_opV_n)
[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=19040248)
# DES Cipher - Java Implementation

This project provides a basic implementation of the DES (Data Encryption Standard) cipher in Java.

## 📁 File: `DESCipher.java`

### 🔐 What It Does

- Encrypts and decrypts plaintext using the DES algorithm.
- Works with both hex and plain text.
- Adds padding if text is not a multiple of 8.
- Converts between text and hex.
- Can write results to a file.

---

## 🔧 Class Responsibilities

### `keyGeneration(String input)`
- Converts a key to binary.
- Generates 16 subkeys using PC1 and PC2 transformations.

### `encrypt(String plainText, String key)`
- Encrypts a hex string.
- Runs through 16 rounds of DES encryption.

### `decrypt(String cipherText, String key)`
- Decrypts a hex string.
- Reverses DES encryption rounds.

### `encrypts(String plainText, String key)`
- Encrypts regular text.
- Pads text to be a multiple of 8.
- Converts to hex and encrypts block-by-block.

### `decrypts(String cipherText, String key)`
- Decrypts regular text in 8-character blocks.
- Converts hex to characters.

### `arrayToString(String plainText, ArrayList<Integer> list2)`
- Builds a final string from characters and integers.
- Keeps non-letter characters in place.

### `addX(String x, int repeat)`
- Adds "X" padding to the end of a string.

### `writeFile(String content, String path)`
- Saves output to a file at the given path.

---

## 📦 Package

```java
package com.example.descipher;
