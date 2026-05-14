package vn.edu.nlu.fit.elearning.common.helper;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.nlu.fit.elearning.common.utils.format.DataFormatting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;

public class PdfHelper {
    private static final String DEST_PATH = "D:\\JAVA\\WEB_EX\\e-learning\\src\\main\\webapp\\assets\\certificates\\";
    private static final String FONT_PATH = "D:\\JAVA\\WEB_EX\\e-learning\\src\\main\\webapp\\assets\\fonts\\static\\Roboto-Semibold.ttf";
    private static final String TEMPLATE_PATH = "D:\\JAVA\\WEB_EX\\e-learning\\src\\main\\webapp\\assets\\image\\certificate-template.png";

    //Khi deploy thì phải dùng cái File.separator do sẽ không biết chạy trên HĐH là window hay linux
    private static final String RELATIVE_CERT_DIR = "assets" + File.separator + "certificates";
    private static final String RELATIVE_FONT_PATH = "assets" + File.separator + "fonts" + File.separator + "static" + File.separator + "Roboto-Semibold.ttf";
    private static final String RELATIVE_TEMPLATE_PATH = "assets" + File.separator + "image" + File.separator + "certificate-template.png";

    private static final Logger logger = LoggerFactory.getLogger(PdfHelper.class);

    public static class PdfResult {
        public String filePath;
        public String errorMessage;
        public boolean success;

        public static PdfResult success(String url) {
            PdfResult r = new PdfResult();
            r.filePath = url;
            r.success = true;
            return r;
        }

        public static PdfResult error(String msg) {
            PdfResult r = new PdfResult();
            r.errorMessage = msg;
            r.success = false;
            return r;
        }

    }

    public static PdfResult generateCertificate(String realPath, String userName, String courseName, Timestamp completionDate, String certCode) throws IOException, Exception {
        try {
            //Tạo ra các đường dẫn vật lý tuyệt đối theo máy chủ đang chạy
            String destDir = realPath + File.separator + RELATIVE_CERT_DIR;
            String absolutePdfPath = destDir + File.separator + certCode + ".pdf";

            String fontPath = realPath + File.separator + RELATIVE_FONT_PATH;
            String templatePath = realPath + File.separator + RELATIVE_TEMPLATE_PATH;

            String dbRelativePath = "/assets/certificates/" + certCode + ".pdf";

            // KIỂM TRA SỰ TỒN TẠI CỦA FOLDER
            File directory = new File(destDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            //Tạo ra tờ giấy A4
            Document document = new Document(PageSize.A4.rotate());
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(absolutePdfPath));
            document.open();

            //Dán cái ảnh template đó lên tờ giấy A4
            Image bgImage = Image.getInstance(templatePath);
            bgImage.scaleAbsolute(PageSize.A4.rotate().getWidth(), PageSize.A4.rotate().getHeight());
            bgImage.setAbsolutePosition(0, 0);
            document.add(bgImage);

            //Chuẩn bị các công cụ để viết đè lên
            PdfContentByte canvas = writer.getDirectContent();
            //Lấy Font chữ
            BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

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

            // Kiểm tra file đã được tạo hay chưa
            File createdFile = new File(absolutePdfPath);

            if (createdFile.exists() && createdFile.length() > 0) {
                logger.info("Tạo PDF thành công: " + absolutePdfPath);
                return PdfResult.success(dbRelativePath);
            } else {
                logger.error("PDF chưa được tạo hoặc file rỗng!");
                return PdfResult.error("Không thể tạo file PDF");
            }
        } catch (Exception e) {
            logger.error("Lỗi PdfHelper: " + String.valueOf(e.getMessage()));
            return PdfResult.error("Lỗi hệ thống " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
//        try {
//            generateCertificate("", "Hoàng Danh Tài", "Biết mình biết ta trăm trận trăm thắng", new Timestamp(System.currentTimeMillis()), "CERT-09-XRLM-2026000");
//            System.out.println("Đã in xong! Bạn hãy mở ổ D:\\TestPDF ra xem nhé.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}


