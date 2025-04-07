# 🔐 DES Cipher - Java Implementation

A simple DES (Data Encryption Standard) encryption and decryption library in Java.

---

## 📁 Files

### `KeyGeneration.java`

Handles the overall encryption and decryption processes.

#### 🔑 Features

- Encrypts and decrypts both plain and hexadecimal text.
- Pads messages to 8 characters.
- Uses 16 rounds of DES encryption.
- Converts between text, binary, and hex.
- Supports writing output to files.

---

### `KeyGeneration.java`

Handles key setup for DES encryption.

#### 🔧 What It Does

- **`hexToBinaryArray(String)`**  
  Converts each hex character to a 4-bit binary string.

- **`PC1(String[])`**  
  Applies the PC1 permutation on the binary key.

- **`DivideToL0R0(String)`**  
  Splits the key into left and right halves.

- **`holdKeyLeftAndRight(String[])`**  
  Generates 16 key pairs by left shifting each half.

- **`numOfLeftShift(String, int)`**  
  Rotates a string left by the given shift amount.

- **`concatenate(String[][])`**  
  Merges left and right halves into full 56-bit keys.

- **`PC2(String[])`**  
  Applies the PC2 permutation to produce final 48-bit subkeys.

---

## 📦 Package

```java
package com.example.KeyGeneration;
