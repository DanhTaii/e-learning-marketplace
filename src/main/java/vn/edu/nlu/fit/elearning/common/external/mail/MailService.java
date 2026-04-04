package vn.edu.nlu.fit.elearning.common.external.mail;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;
import java.util.Random;

public class MailService {
    public static final int LIMIT_MINUTE = 1;

    public static String generateToken() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder token = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            token.append(chars.charAt(random.nextInt(chars.length())));
        }

        return token.toString();
    }


    public static boolean sendEmail(String email, String code, String name) {

        Properties prop = new Properties();
        prop.setProperty("mail.smtp.host", "smtp.gmail.com");
        prop.setProperty("mail.smtp.port", "587");
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.connectiontimeout", "10000");
        prop.put("mail.smtp.timeout", "10000");
        prop.put("mail.smtp.writetimeout", "10000");
        prop.put("mail.debug", "true");


        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(System.getenv("EMAIL_HOST"), System.getenv("EMAIL_PASSWORD"));
            }
        };

        Session session = Session.getInstance(prop, auth);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-Type", "text/html; charset=UTF-8");
            msg.setFrom(new InternetAddress(System.getenv("EMAIL_HOST")));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
            msg.setSubject("Mã xác nhận đăng ký", "UTF-8");

            // Nội dung chỉ gửi mã code
            String content = "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: auto; border: 1px solid #eee; padding: 20px;'>"
                    + "<h2 style='color: #4CAF50; text-align: center;'>Xác Thực Tài Khoản Softskill</h2>"
                    + "<p>Xin chào <b>" + name + "</b>,</p>"
                    + "<p>Cảm ơn bạn đã đăng ký tham gia cộng đồng học tập kỹ năng mềm của chúng tôi. Đây là mã xác nhận của bạn:</p>"
                    + "<div style='text-align: center; margin: 30px 0;'>"
                    + "<span style='font-size: 24px; font-weight: bold; letter-spacing: 5px; background: #f4f4f4; padding: 10px 20px; border-radius: 5px; border: 1px dashed #4CAF50;'>" + code + "</span>"
                    + "</div>"
                    + "<p style='color: #ff0000;'>Lưu ý: Mã này sẽ hết hạn sau " + LIMIT_MINUTE + " phút.</p>"
                    + "<hr style='border: none; border-top: 1px solid #eee;'>"
                    + "<p style='font-size: 12px; color: #888; text-align: center;'>Đây là email tự động, vui lòng không phản hồi email này.</p>"
                    + "</div>";

            msg.setContent(content, "text/html; charset=UTF-8");
            Transport.send(msg);
            System.out.println("Sent successfully");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.err);
            throw new RuntimeException("Lỗi gửi Email: " + e.getMessage(), e);
        }
    }



}
