package edu.hw8.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPasswordCracker {
    protected final Map<String, String> encryptedPasswords;
    protected Map<String, String> crackedPasswords;

    protected AbstractPasswordCracker(Map<String, String> encryptedPasswords) {
        if (encryptedPasswords == null) {
            throw new IllegalArgumentException("Карта зашифрованных паролей не может быть null");
        }
        this.encryptedPasswords = new HashMap<>(encryptedPasswords);
    }

    public abstract Map<String, String> crack();

    protected void tryFindEncryptedPassword(String password) {
        String encryptedPassword = encrypt(password);
        if (encryptedPasswords.containsKey(encryptedPassword)) {
            crackedPasswords.put(encryptedPasswords.get(encryptedPassword), password);
            encryptedPasswords.remove(encryptedPassword);
        }
    }

    @SuppressWarnings("MagicNumber")
    private String encrypt(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Ошибка при вычислении хэша MD5");
        }
    }
}
