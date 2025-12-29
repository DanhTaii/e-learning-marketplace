package vn.edu.nlu.fit.elearning.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

    private static final String SALT = "SECRET";

    public static String hashpassword(String password) {
        try {
            String saltPassword = password + SALT;
            //Tạo đối tượng MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Đổi chuỗi sang byte cho máy dễ hiểu hơn
            byte[] bytesOfMessage = saltPassword.getBytes(StandardCharsets.UTF_8);

            //Thực hiện băm chuỗi đầu vào
            //Trả về 1 mảng 16 byte (128 bit)
            //Ví dụ: 9IYVW 8
            byte[] theMD5digest = md.digest(bytesOfMessage);

            // Chuyển đổi mảng byte sang dạng Hex (thập lục phân - từ 0-9 và a-f)
            //để lưu vào Database dưới dạng chuỗi 32 ký tự.
            // Ví dụ: e10adc3949ba59abbe56e057f20f883e
            //Lý do:
            // + Thông thường DB sẽ có dạng VARCHAR/TEXT chỉ đc được số và ký tự
            // + Mảng 16 byte sẽ có các byte không có trong bảng mã ASCII hoặc tệ hơn là NUL, \n , . . .
            StringBuilder sb = new StringBuilder();
            for (byte b : theMD5digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Lỗi thuật toán MD5", e);
        }
    }
}
