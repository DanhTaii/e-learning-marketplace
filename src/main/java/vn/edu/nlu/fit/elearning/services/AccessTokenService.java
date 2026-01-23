package vn.edu.nlu.fit.elearning.services;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import vn.edu.nlu.fit.elearning.dao.AccessTokenDao;
import vn.edu.nlu.fit.elearning.model.AccessToken;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

public class AccessTokenService {
    private final int LIMIT_MINUTE = 10;
    String emailFrom = "minh6112005@gmail.com";
    String password = "fppq kvfg qevh hdxv";

    // Thêm DAO để dùng cho validate & mark used
    private final AccessTokenDao tokenDao = new AccessTokenDao();

    public String generateToken() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder token = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            token.append(chars.charAt(random.nextInt(chars.length())));
        }

        return token.toString();
    }

    public String generateTokenForVerify() {
        return UUID.randomUUID().toString();
    }

    public Timestamp expireDateTime() {
        return Timestamp.valueOf(LocalDateTime.now().plusMinutes(LIMIT_MINUTE));
    }

    public boolean isExpireTime(Timestamp expireTime){
        if(expireTime==null) return false;
        return LocalDateTime.now().isAfter(expireTime.toLocalDateTime().plusMinutes(LIMIT_MINUTE));
    }

    public boolean sendEmail(String email, String code, String name, boolean isLinkMode){

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
            if (isLinkMode) {
                msg.setSubject("Xác nhận email", "UTF-8");
            } else {
                msg.setSubject("Tạo lại mật khẩu", "UTF-8");
            }

            String content;
            if (isLinkMode) { // gửi link xác nhận
                String link = "http://localhost:8080/e_learning_war_exploded/sign-in";
                content = "<h1>Hello " + name + "</h1>" + "<p>Click the link to verify your email: "
                        + "<a href='" + link + "'>Click here</a></p>";
            } else { // gửi mã reset
                content = "<h1>Hello " + name + "</h1>" + "<p>Your password reset verification code is: " + code + "</p>";
            }

            msg.setContent(content, "text/html; charset=UTF-8");
            Transport.send(msg);

            System.out.println("Sent successfully");
            return true;
        } catch (Exception e){
            System.out.println("Sent unsuccessfully");
            e.printStackTrace();
            return false;
        }
    }

    public boolean validateResetToken(int userId, String token) {
        AccessToken accessToken = tokenDao.findByUserIdAndToken(userId, token);
        if (accessToken == null) {
            return false;
        }
        // Đã dùng rồi
        if (accessToken.isUsed()) {
            return false;
        }
        // Hết hạn (dùng method cũ của bạn)
        if (isExpireTime(accessToken.getExpiriTime())) {
            return false;
        }
        return true;
    }

    public boolean markAsUsed(String token) {
        return tokenDao.markAsUsed(token);
    }
}