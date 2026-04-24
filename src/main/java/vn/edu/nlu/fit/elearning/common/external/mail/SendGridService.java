package vn.edu.nlu.fit.elearning.common.external.mail;

import com.sendgrid.SendGrid;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.Method;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Content;

import java.io.IOException;
import java.security.SecureRandom;

public class SendGridService {

    public static final int LIMIT_MINUTE = 1;

    public static String generateToken() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder token = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 5; i++) {
            token.append(chars.charAt(random.nextInt(chars.length())));
        }

        return token.toString();
    }

    public static boolean sendEmail(String email, String code, String name) {

        Email from = new Email("no-reply@e-learning.id.vn");

        String subject = "Mã xác nhận đăng ký";

        Email to = new Email(email);

        String htmlContent =
                "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: auto; border: 1px solid #eee; padding: 20px;'>"
                        + "<h2 style='color: #4CAF50; text-align: center;'>Xác Thực Tài Khoản Softskill</h2>"
                        + "<p>Xin chào <b>" + name + "</b>,</p>"
                        + "<p>Đây là mã xác nhận của bạn:</p>"
                        + "<div style='text-align: center; margin: 30px 0;'>"
                        + "<span style='font-size: 24px; font-weight: bold; letter-spacing: 5px; background: #f4f4f4; padding: 10px 20px; border-radius: 5px; border: 1px dashed #4CAF50;'>"
                        + code + "</span>"
                        + "</div>"
                        + "<p style='color: #ff0000;'>Mã sẽ hết hạn sau " + LIMIT_MINUTE + " phút.</p>"
                        + "</div>";

        Content content = new Content("text/html", htmlContent);

        Mail mail = new Mail(from, subject, to, content);

        // API KEY từ ENV
        SendGrid sg = new SendGrid(System.getenv("SEND_GRID_KEY"));

        try {
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            System.out.println("Status Code: " + response.getStatusCode());

            return response.getStatusCode() == 202;

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi gửi email SendGrid: " + e.getMessage(), e);
        }
    }
    public static boolean sendPaymentSuccessEmail(String email, String name, String orderCode, long totalAmount) {
        Email from = new Email("no-reply@e-learning.id.vn");
        String subject = "Xác nhận thanh toán thành công - Softskill";
        Email to = new Email(email);

        String formattedAmount = String.format("%,d VND", totalAmount);

        String htmlContent =
                "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: auto; border: 1px solid #eee; padding: 20px;'>"
                        + "<h2 style='color: #4CAF50; text-align: center;'>Thanh Toán Thành Công!</h2>"
                        + "<p>Xin chào <b>" + name + "</b>,</p>"
                        + "<p>Cảm ơn bạn đã mua khóa học tại Softskill. Đơn hàng của bạn đã được thanh toán thành công và quyền truy cập khóa học đã được kích hoạt.</p>"
                        + "<div style='background: #f4f4f4; padding: 15px; border-radius: 5px; margin: 20px 0; border-left: 4px solid #4CAF50;'>"
                        + "<p style='margin: 5px 0;'><b>Mã đơn hàng:</b> " + orderCode + "</p>"
                        + "<p style='margin: 5px 0;'><b>Tổng tiền:</b> <span style='color: #e53935; font-weight: bold;'>" + formattedAmount + "</span></p>"
                        + "<p style='margin: 5px 0;'><b>Trạng thái:</b> <span style='color: #4CAF50; font-weight: bold;'>Đã thanh toán (PAID)</span></p>"
                        + "</div>"
                        + "<p>Bạn có thể đăng nhập vào hệ thống để bắt đầu học ngay bây giờ.</p>"
                        + "<p>Chúc bạn học tốt!</p>"
                        + "</div>";

        Content content = new Content("text/html", htmlContent);
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(System.getenv("SEND_GRID_KEY"));

        try {
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);
            return response.getStatusCode() == 202;

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Lỗi gửi email SendGrid: " + e.getMessage());
            return false;
        }
    }
}