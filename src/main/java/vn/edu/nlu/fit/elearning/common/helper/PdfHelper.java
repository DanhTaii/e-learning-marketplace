package vn.edu.nlu.fit.elearning.common.helper;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
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
    private static final String RELATIVE_FONT_PATH = "assets" + File.separator + "fonts" + File.separator + "static" + File.separator + "Roboto-SemiBold.ttf";
    private static final String RELATIVE_TEMPLATE_PATH = "assets" + File.separator + "image" + File.separator + "certificate-template.png";
    public static final String CERTIFICATE_STORAGE = "/opt/elearning/certificates";

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

    // Thư viện iTextPDF yêu cầu truyền kiểu dữ liệu float nên mỗi cái đều phải thêm f ở cuối
    // Các số nhập vào thì bên iTextPDF đang tính là Point
    // 72 points = 1 inch = 2.54 cm
    // Một tờ giấy A4 tiểu chuẩn là 210mm x 297mm.
    // Khi đưa vào iTextPDF thì tờ giấy nằm dọc: Rộng 595 pt x Cao 842 pt.
    // Mà chứng chỉ là A4 nằm ngang (rotate) nên nó lật ngược lại: Rộng 842 pt x Cao 595 pt.
    // Trục X (Ngang): Chạy từ 0 (mép trái) đến 842 (mép phải).
    // Trục Y (Dọc): Chạy từ 0 (mép dưới) đến 595 (mép trên).
    public static PdfResult generateCertificate(String realPath, String userName, String courseName, Timestamp completionDate, String certCode) throws IOException, Exception {
        try {
            //Tạo ra các đường dẫn vật lý tuyệt đối theo máy chủ đang chạy
//            String destDir = realPath + File.separator + RELATIVE_CERT_DIR;
            String destDir = CERTIFICATE_STORAGE;
            String absolutePdfPath = destDir + File.separator + certCode + ".pdf";

            String fontPath = realPath + File.separator + RELATIVE_FONT_PATH;
            String templatePath = realPath + File.separator + RELATIVE_TEMPLATE_PATH;

//            String dbRelativePath = "/assets/certificates/" + certCode + ".pdf";
            String dbRelativePath = certCode + ".pdf";

            // KIỂM TRA SỰ TỒN TẠI CỦA FOLDER
            File directory = new File(destDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            //Tạo ra tờ giấy A4 nằm ngang (rotate)
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

            // TÍNH TOÁN KHÔNG GIAN TỐI ĐA CHO CHỮ HIỂN THỊ

            // Điểm chính giữa tờ A4: 842 / 2 = 421f
            float centerX = 421f;
            // Bề rộng tối đa của khung chứa chữ (tránh đâm ra lề xám)
            float maxContentWidth = 650f;

            //VIẾT TÊN HỌC VIÊN (dùng hàm AutoFit, trong hàm này đã tự gọi begin/end text rồi nên không cần gọi lại)
            canvas.setColorFill(BaseColor.BLACK);
            renderTextAutoFit(canvas, bf, userName, centerX, 380f, 40f, maxContentWidth);

            //VIẾT TÊN KHÓA HỌC (dùng hàm MultiLine)
            BaseColor brandBlue = new BaseColor(0, 86, 210);
            renderTextMultiLine(canvas, bf, brandBlue, courseName, centerX, 250f, maxContentWidth, 26f);

            // 3. VIẾT NGÀY THÁNG & MÃ CHỨNG CHỈ
            canvas.beginText();
            // Đưa màu chữ vào
            canvas.setColorFill(BaseColor.BLACK);
            // Set kiểu và cỡ chữ
            canvas.setFontAndSize(bf, 14);
            // Viết ngày hoàn thành
            canvas.showTextAligned(Element.ALIGN_LEFT, DataFormatting.formatTimestamp(completionDate), 75, 80, 0);
            // Viết mã chứng chỉ
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

    /**
     * Tự động rớt xuống dòng bằng cách vẽ hộp giới hạn
     */
    private static void renderTextAutoFit(PdfContentByte canvas, BaseFont bf, String text, float x, float y, float initialSize, float maxWidth) {
        float fontSize = initialSize;
        // Rút nhỏ dần size chữ cho đến khi bề ngang chữ nhỏ hơn maxWidth
        while (bf.getWidthPoint(text, fontSize) > maxWidth && fontSize > 15f) {
            fontSize -= 1f;
        }
        canvas.beginText();
        canvas.setFontAndSize(bf, fontSize);
        canvas.showTextAligned(Element.ALIGN_CENTER, text, x, y, 0);
        canvas.endText();
    }

    /**
     * Tự động rớt xuống dòng bằng cách vẽ hộp giới hạn
     */
    private static void renderTextMultiLine(PdfContentByte canvas, BaseFont bf, BaseColor color, String text,
                                            float centerX, float centerY, float width, float fontSize) {
        try {
            Font font = new Font(bf, fontSize, Font.NORMAL, color);
            // Xem nó như là CSS; nó sẽ nhận các màu, cỡ chữ, font rồi style chữ đó
            Phrase phrase = new Phrase(text, font);

            // Leading là thuật ngữ trong ngành in ấn, chỉ khoảng cách giữa các dòng chữ
            // Gioongs line-height bên css
            float leading = fontSize + 10f;

            // Với centerX mặc định là 421f và Chiều ngang mặc định là 650f
            // Cứ hiểu 650f này sẽ nằm ở giữa chiều ngang
            // Tọa độ X của mép trái hộp: 421 - (650 / 2) = 96
            float llx = centerX - (width / 2);
            // Tọa độ X của mép phải hộp: 421 + (650 / 2) = 746
            float urx = centerX + (width / 2);

            // VẼ NHÁP ĐỂ ĐẾM XEM CÓ BAO NHIÊU DÒNG
            // ColumnText trong iTextPDF được xem như hộp để vẽ rớt dòng và nó không xử lý String nên cần Phrase
            ColumnText ctDummy = new ColumnText(canvas);
            ctDummy.setSimpleColumn(phrase, llx, 0, urx, 2000f, leading, Element.ALIGN_CENTER);
            ctDummy.go(true); // true = Chỉ nháp, không in ra PDF

            // Lấy số dòng thực tế
            int lines = ctDummy.getLinesWritten();

            // TÍNH TOÁN TRẦN HỘP
            // centerY lúc này mặc định là 250f
            // Đỉnh của trần hộp
            // Công thức: ury = centerY + ((số dòng - 1) * một_nửa_line_height) + line_height
            // Ví dụ: Nếu chỉ có 1 dòng: centerY + leading (line_height)
            // => Đưa vào đúng chỗ centerY đã tính toán
            // Ví dụ: Nếu có 2 dòng thì centerY + 1*(leading/2) + leading
            // => Kêu đỉnh dời lên trên 1 nửa cái leading hiện tại để chứa chữ
            float ury = centerY + ((lines - 1) * (leading / 2f)) + leading;
            // Đáy hộp miễn là đủ sâu để chữ không đụng đáy là được
            // Do chữ đã được đưa từ trên xuống nên không được cao hơn chỗ chữ rớt
            // Ví dụ chữ tới 200f mà bạn để 220f là không được
            // Có thể gán lly = 0 nhưng nên để nó đối xứng với công thức đỉnh hộp để xác định chính xác
            float lly = centerY - ((lines - 1) * (leading / 2f)) - leading; // Cho đáy dư dả

            // VẼ THẬT
            ColumnText ctReal = new ColumnText(canvas);
            ctReal.setSimpleColumn(phrase, llx, lly, urx, ury, leading, Element.ALIGN_CENTER);
            ctReal.go(false); // false = In thẳng ra PDF

        } catch (DocumentException e) {
            logger.error("Lỗi khi render MultiLine: " + e.getMessage());
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


