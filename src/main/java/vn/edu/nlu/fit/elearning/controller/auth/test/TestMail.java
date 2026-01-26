package vn.edu.nlu.fit.elearning.controller.auth.test;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class TestMail {
    public static void main(String[] args) {
        try {
            // Copy cái logic gửi mail trong AccessTokenService của ông vào đây
            // Thử cấu hình cổng 465 (SSL)
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            // Đăng nhập với App Password (16 ký tự)
            Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("minh6112005@gmail.com", "rwag rmgi tlfw xbmn");
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("email_cua_ong@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("email_nhan@gmail.com"));
            message.setSubject("Test nhanh cổng 465");
            message.setText("Nếu nhận được thì chúc mừng ông giáo!");

            Transport.send(message);
            System.out.println("====== GỬI THÀNH CÔNG! ======");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("====== VẪN TỊT! ======");
        }
    }
}
