package com.padhuga.tamil.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.padhuga.tamil.models.ParentModel;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class SharedPrefsSecurity {

    public static class SecurePreferencesException extends RuntimeException {
        SecurePreferencesException(Throwable e) {
            super(e);
        }
    }

    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String KEY_TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String SECRET_KEY_HASH_TRANSFORMATION = "SHA-256";
    private static final String CHARSET = "UTF-8";
    private static Gson GSON;

    private boolean encryptKeys;
    private Cipher writer;
    private Cipher reader;
    private Cipher keyWriter;
    private SharedPreferences preferences;

    public SharedPrefsSecurity(Context context, String preferenceName, String secureKey, boolean encryptKeys) throws SecurePreferencesException {
        try {
            this.writer = Cipher.getInstance(TRANSFORMATION);
            this.reader = Cipher.getInstance(TRANSFORMATION);
            this.keyWriter = Cipher.getInstance(KEY_TRANSFORMATION);
            GSON = new Gson();
            initCiphers(secureKey);
            this.preferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
            this.encryptKeys = encryptKeys;
        } catch (GeneralSecurityException | UnsupportedEncodingException e) {
            throw new SecurePreferencesException(e);
        }
    }

    private void initCiphers(String secureKey) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidAlgorithmParameterException {
        IvParameterSpec ivSpec = getIv();
        SecretKeySpec secretKey = getSecretKey(secureKey);

        writer.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        reader.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        keyWriter.init(Cipher.ENCRYPT_MODE, secretKey);
    }

    private IvParameterSpec getIv() {
        byte[] iv = new byte[writer.getBlockSize()];
        System.arraycopy("fldsjfodasjifudslfjdsaofshaufihadsf".getBytes(), 0, iv, 0, writer.getBlockSize());
        return new IvParameterSpec(iv);
    }

    private SecretKeySpec getSecretKey(String key) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] keyBytes = createKeyBytes(key);
        return new SecretKeySpec(keyBytes, TRANSFORMATION);
    }

    private byte[] createKeyBytes(String key) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(SECRET_KEY_HASH_TRANSFORMATION);
        md.reset();
        return md.digest(key.getBytes(CHARSET));
    }

    public void put(String key, String value) {
        if (value == null) {
            preferences.edit().remove(toKey(key)).apply();
        } else {
            putValue(toKey(key), value);
        }
    }

    public void put(String key, Object value){
        if (value == null) {
            preferences.edit().remove(toKey(key)).apply();
        }
        else{
            String jsonData = GSON.toJson(value);
            putValue(toKey(key), jsonData);
        }
    }

    public boolean containsKey(String key) {
        return preferences.contains(toKey(key));
    }

    public void removeValue(String key) {
        preferences.edit().remove(toKey(key)).apply();
    }

    public String getString(String key) throws SecurePreferencesException {
        if (preferences.contains(toKey(key))) {
            String securedEncodedValue = preferences.getString(toKey(key), "");
            return decrypt(securedEncodedValue);
        }
        return null;
    }

    public <T> T getObject(String key, Class<T> a) {
        String gson = getString(key);
        if (gson == null) {
            return null;
        } else {
            try{
                return GSON.fromJson(gson, a);
            } catch (Exception e) {
                throw new IllegalArgumentException("Object storaged with key " + key + " is instanceof other class");
            }
        }
    }

    public void clear() {
        preferences.edit().clear().apply();
    }

    private String toKey(String key) {
        if (encryptKeys)
            return encrypt(key, keyWriter);
        else return key;
    }

    private void putValue(String key, String value) throws SecurePreferencesException {
        String secureValueEncoded = encrypt(value, writer);

        preferences.edit().putString(key, secureValueEncoded).apply();
    }

    private String encrypt(String value, Cipher writer) throws SecurePreferencesException {
        byte[] secureValue;
        try {
            secureValue = convert(writer, value.getBytes(CHARSET));
        } catch (UnsupportedEncodingException e) {
            throw new SecurePreferencesException(e);
        }
        return Base64.encodeToString(secureValue, Base64.NO_WRAP);
    }

    private String decrypt(String securedEncodedValue) {
        byte[] securedValue = Base64.decode(securedEncodedValue, Base64.NO_WRAP);
        byte[] value = convert(reader, securedValue);
        try {
            return new String(value, CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new SecurePreferencesException(e);
        }
    }

    private byte[] convert(Cipher cipher, byte[] bs) throws SecurePreferencesException {
        try {
            return cipher.doFinal(bs);
        } catch (Exception e) {
            throw new SecurePreferencesException(e);
        }
    }

    public void initSharedPrefsSecurity(Context context) {
        SharedPrefsSecurity preferences = new SharedPrefsSecurity(context, "tamil_user_preference", "Padhugadhasan@555*%&", true);
        preferences.put("userIdIntToString", String.valueOf(1));
        preferences.put("userIdString", "userid1234");
        String user = preferences.getString("userId");
        Log.d("Bharani user", user);
    }

    public void initSharedPrefsSecurityObject(Context context, ParentModel parentModel) {
        SharedPrefsSecurity preferences = new SharedPrefsSecurity(context, "tamil_user_preference", "Padhugadhasan@555*%&", true);
        preferences.put("jsonObject", parentModel);
        ParentModel parentModel1 = preferences.getObject("jsonObject", ParentModel.class);
//        Log.d("Bharani JsonData", parentModel1.data.desc);
    }
}

