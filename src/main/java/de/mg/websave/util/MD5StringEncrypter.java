package de.mg.websave.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5StringEncrypter {

    final static String ALGORITHM = "MD5";
    final static String ENCODING = "UTF-8";

    public static String encrypt(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            digest.update(input.getBytes(ENCODING));
            byte[] hash = digest.digest();
            return hexEncode(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    // Convert given byteArray into an nice String-representation
    private static String hexEncode(byte[] input) {
        StringBuilder result = new StringBuilder();
        char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
                'f'};
        int idx = 0;
        while (idx < input.length) {
            byte b = input[idx];
            result.append(digits[(b & 0xf0) >> 4]);
            result.append(digits[b & 0x0f]);
            ++idx;
        }
        return result.toString();
    }

}