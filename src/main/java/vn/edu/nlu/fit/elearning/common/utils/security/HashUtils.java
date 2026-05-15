package vn.edu.nlu.fit.elearning.common.utils.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class HashUtils {

    private static final String SALT = "SECRET";

    public static String generateTokenForVerify() {
        return UUID.randomUUID().toString();
    }

    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytesOfMessage = input.getBytes(StandardCharsets.UTF_8);
            byte[] theMD5digest = md.digest(bytesOfMessage);

            StringBuilder sb = new StringBuilder();
            for (byte b : theMD5digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Lỗi thuật toán MD5", e);
        }
    }
    public static String hashpassword(String password) {
        String saltPassword = password + SALT;
        return md5(saltPassword);
    }

    public static void validatePassword(String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("Mật khẩu phải có ít nhất 8 ký tự");
        }

        if (!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Mật khẩu phải chứa ít nhất 1 chữ thường và 1 chữ hoa");
        }

        if (!password.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("Mật khẩu phải chứa ít nhất 1 chữ số");
        }

        if (!password.matches(".*[^A-Za-z0-9].*")) {
            throw new IllegalArgumentException("Mật khẩu phải chứa ít nhất 1 ký tự đặc biệt");
        }
    }

}
