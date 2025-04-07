# PTBlockX - DES Cipher Implementation

## Overview
A Java implementation of core DES (Data Encryption Standard) cipher operations including:

- Hexadecimal to binary conversion
- Initial Permutation (IP)
- Feistel network rounds
- S-box substitutions
- Final permutation

## Key Features
- `convertHexToBinary()`: Converts hex strings to binary
- `IP()`: Performs initial permutation
- `L0R0()`: Splits input into left/right halves
- `iterationWithNumOfRound()`: Handles encryption/decryption rounds
- `ManaglerFun()`: Implements Feistel function
- `S_Box()`: Performs substitution using DES S-boxes
- `P()`: Permutation function
- `finalInverseP()`: Final permutation

## Usage
```java
PTBlockX cipher = new PTBlockX();
String binary = cipher.convertHexToBinary("1A2B3C");
String permuted = cipher.IP(binary);