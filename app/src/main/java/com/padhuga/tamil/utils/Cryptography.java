package com.padhuga.tamil.utils;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Cryptography {

    private static byte[] generateKey(String key) throws GeneralSecurityException, UnsupportedEncodingException {
        byte[] binary = key.getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        binary = sha.digest(binary);
        binary = Arrays.copyOf(binary, 16);
        return binary;
    }

    private static String encrypt(byte[] key, String value) throws GeneralSecurityException {
        // Argument validation.
        if (key.length != 16) {
            throw new IllegalArgumentException("Invalid key size.");
        }

        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16]));

        byte[] original = value.getBytes(Charset.forName("UTF-8"));
        byte[] binary = cipher.doFinal(original);
        return Base64.encodeToString(binary, Base64.DEFAULT);
    }

    private static String decrypt(byte[] key, String encrypted) throws GeneralSecurityException {

        if (key.length != 16) {
            throw new IllegalArgumentException("Invalid key size.");
        }

        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16]));

        byte[] binary = Base64.decode(encrypted, Base64.DEFAULT);
        byte[] original = cipher.doFinal(binary);
        return new String(original, Charset.forName("UTF-8"));
    }

    public void checkCryptoGraphy() throws GeneralSecurityException, UnsupportedEncodingException {
        byte[] key = generateKey("Bharani");
        String encryptedString = encrypt(key, "Hello World");
        Log.d("Bharani encryptedString", encryptedString);
        String decryptedString = decrypt(key, encryptedString);
        Log.d("Bharani decryptedString", decryptedString);
    }
}
