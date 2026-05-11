package vn.edu.nlu.fit.elearning.common.helper;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import vn.edu.nlu.fit.elearning.common.utils.format.DataFormatting;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;

public class PdfHelper {
    private static final String DEST_PATH = "D:\\JAVA\\WEB_EX\\e-learning\\src\\main\\webapp\\assets\\certificates\\";
    private static final String FONT_PATH = "D:\\JAVA\\WEB_EX\\e-learning\\src\\main\\webapp\\assets\\fonts\\static\\Roboto-Semibold.ttf";
    private static final String TEMPLATE_PATH = "D:\\JAVA\\WEB_EX\\e-learning\\src\\main\\webapp\\assets\\image\\certificate-template.png";

    public static void generateCertificate(String userName, String courseName, Timestamp completionDate, String certCode) throws IOException, Exception {
        try {
            //Tạo ra tờ giấy A4
            Document document = new Document(PageSize.A4.rotate());
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST_PATH + certCode + ".pdf"));
            document.open();

            //Dán cái ảnh template đó lên tờ giấy A4
            Image bgImage = Image.getInstance(TEMPLATE_PATH);
            bgImage.scaleAbsolute(PageSize.A4.rotate().getWidth(), PageSize.A4.rotate().getHeight());
            bgImage.setAbsolutePosition(0,0);
            document.add(bgImage);

            //Chuẩn bị các công cụ để viết đè lên
            PdfContentByte canvas = writer.getDirectContent();
            //Lấy Font chữ
            BaseFont bf = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            // VIẾT CHỮ
            canvas.beginText();
            canvas.setFontAndSize(bf, 40); // Size chữ 40

            // Tham số lần lượt: (Canh lề, Nội dung, Tọa độ X, Tọa độ Y, Góc xoay)
            // 421 là điểm giữa của tờ A4 ngang (842 / 2).
            // 380 là chiều cao từ dưới đáy đếm lên.
            canvas.showTextAligned(Element.ALIGN_CENTER, userName, 421, 380, 0);

            BaseColor brandBlue = new BaseColor(0, 86, 210);
            canvas.setColorFill(brandBlue);
            canvas.showTextAligned(Element.ALIGN_CENTER, courseName, 421, 250, 0);

            canvas.setColorFill(BaseColor.BLACK);
            canvas.setFontAndSize(bf, 14); // Size chữ 10
            canvas.showTextAligned(Element.ALIGN_LEFT, DataFormatting.formatTimestamp(completionDate), 75, 80, 0);

            canvas.showTextAligned(Element.ALIGN_RIGHT, certCode, 767, 80, 0);

            canvas.endText();
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        try {
            generateCertificate("Hoàng Danh Tài", "Biết mình biết ta trăm trận trăm thắng", new Timestamp(System.currentTimeMillis()), "CERT-09-XRLM-2026000");
            System.out.println("Đã in xong! Bạn hãy mở ổ D:\\TestPDF ra xem nhé.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


