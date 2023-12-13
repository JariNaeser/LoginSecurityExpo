package ch.supsi.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class PasswordEncrypter {
    public static String encrypt(String key) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return Arrays.toString(md.digest(key.getBytes()));
    }
}
