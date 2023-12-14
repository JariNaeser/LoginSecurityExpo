package ch.supsi.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class PasswordHelper {
    public static String encrypt(String value, String salt) throws NoSuchAlgorithmException {
        String saltedValue = value + salt;

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(saltedValue.getBytes(StandardCharsets.UTF_8));

        return Arrays.toString(hash);
    }
}
