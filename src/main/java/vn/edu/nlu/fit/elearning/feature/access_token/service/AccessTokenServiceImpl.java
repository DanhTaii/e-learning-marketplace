package vn.edu.nlu.fit.elearning.feature.access_token.service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import vn.edu.nlu.fit.elearning.feature.access_token.dao.AccessTokenDaoImpl;
import vn.edu.nlu.fit.elearning.feature.access_token.dao.AccessTokenDao;
import vn.edu.nlu.fit.elearning.feature.access_token.model.AccessToken;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

public class AccessTokenServiceImpl implements AccessTokenService {
    private AccessTokenDao accessTokenDao;
    private final int LIMIT_MINUTE = 1;
    String emailFrom = "minh6112005@gmail.com";
    String password = "zwbo jmsn tlpr mieh";

    public AccessTokenServiceImpl(AccessTokenDao accessTokenDao) {
        this.accessTokenDao = accessTokenDao;
    }

    @Override
    public String generateToken() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder token = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            token.append(chars.charAt(random.nextInt(chars.length())));
        }

        return token.toString();
    }

    @Override
    public int createToken(AccessToken token) {
        return accessTokenDao.create(token);
    }

    @Override
    public String generateTokenForVerify() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Timestamp expireDateTime() {
        return Timestamp.valueOf(LocalDateTime.now().plusMinutes(LIMIT_MINUTE));
    }

    @Override
    public boolean isExpireTime(Timestamp expireTime) {
        if (expireTime == null) return false;
        return LocalDateTime.now().isAfter(expireTime.toLocalDateTime().plusMinutes(LIMIT_MINUTE));
    }

    @Override
    public boolean sendEmail(String email, String code, String name) {

        Properties prop = new Properties();
        prop.setProperty("mail.smtp.host", "smtp.gmail.com");
        prop.setProperty("mail.smtp.port", "587");
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailFrom, password);
            }
        };

        Session session = Session.getInstance(prop, auth);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-Type", "text/html; charset=UTF-8");
            msg.setFrom(new InternetAddress(emailFrom));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
            msg.setSubject("Mã xác nhận đăng ký", "UTF-8");

            // Nội dung chỉ gửi mã code
            String content ="<div style='font-family: Arial, sans-serif; max-width: 600px; margin: auto; border: 1px solid #eee; padding: 20px;'>"
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
            System.out.println("Sent unsuccessfully");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean validateResetToken(int userId, String token) {
        AccessToken accessToken = accessTokenDao.findByUserIdAndToken(userId, token);
        if (accessToken == null) return false;
        if (accessToken.isUsed()) return false;
        if (isExpireTime(accessToken.getExpiriTime())) return false;
        return true;
    }

    // Dùng cho đăng ký (không cần userId)
    @Override
    public boolean validateSignupToken(String token) {
        AccessToken accessToken = accessTokenDao.findByToken(token);
        if (accessToken == null) return false;
        if (accessToken.isUsed()) return false;
        if (isExpireTime(accessToken.getExpiriTime())) return false;
        return true;
    }

    @Override
    public boolean markAsUsed(String token) {
        return accessTokenDao.markAsUsed(token);
    }
}