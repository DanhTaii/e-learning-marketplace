package vn.edu.nlu.fit.elearning.services;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.UUID;

public class TokenForgetPasswordService {
    private final int LIMIT_MINUTE = 1;
    String emailFrom = "minh6112005@gmail.com";
    String password = "xpfwkobwmpoqascz";

    public String generateToken() {
        return UUID.randomUUID().toString();
    }

    public Timestamp expireDateTime(){
        return Timestamp.valueOf(LocalDateTime.now().plusMinutes(LIMIT_MINUTE));
    }

    public boolean isExpireTime(Timestamp expireTime){
//        return LocalDateTime.now().isAfter(expireTime.toLocalDateTime());
        return LocalDateTime.now().isAfter(expireTime.toLocalDateTime().plusMinutes(LIMIT_MINUTE));
    }

    public boolean sendEmail(String email, String link, String name){
//        String emailFrom = ConfigLoader.getProperty("mail.user");
//        String password = ConfigLoader.getProperty("mail.pass");

        Properties prop = new Properties();
        prop.setProperty("mail.smtp.host", "smtp.gmail.com");
        prop.setProperty("mail.smtp.port", "587");
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.starttls.enable", "true");
        //        prop.put("mail.smtp.host", ConfigLoader.getProperty("mail.smtp.host"));
//        prop.put("mail.smtp.port", ConfigLoader.getProperty("mail.smtp.port"));
//        prop.put("mail.smtp.auth", ConfigLoader.getProperty("mail.smtp.auth"));
//        prop.put("mail.smtp.starttls.enable", ConfigLoader.getProperty("mail.smtp.starttls.enable"));


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
            msg.setSubject("Tạo lại mật khẩu", "UTF-8");

            String content = "<h1>Hello " + name + "</h1>"
                    + "<p>Click the link to reset your password: "
                    + "<a href='" + link + "'>Click here</a></p>";

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
}
