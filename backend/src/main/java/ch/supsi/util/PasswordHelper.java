package ch.supsi.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class PasswordHelper {
    public static String encrypt(String value, String salt) throws NoSuchAlgorithmException {
        String saltedValue = value + salt;

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        md.update(saltedValue.getBytes(StandardCharsets.UTF_8));
        byte[] data = md.digest();

        return getHashString(data);
    }

    private static String getHashString(byte[] data){
        StringBuilder sb = new StringBuilder();

        for (byte b : data) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

    public static String getGeneratedSalt(){
        Random r = new Random();
        String alphabet = "1234567890abcdefghilmnopqrstuvzABCDEFGHILMNOPQRTSTUVZjJkKxX-_!?$£";
        StringBuilder sb = new StringBuilder();

        // Generale 8 char long salt
        for (int i = 0; i < 10; i++) {
            sb.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }

        return sb.toString();
    }
}
