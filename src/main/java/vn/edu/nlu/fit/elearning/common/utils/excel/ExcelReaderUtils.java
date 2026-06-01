package vn.edu.nlu.fit.elearning.common.utils.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class ExcelReaderUtils {

    /**
     * Hàm đọc Excel dùng chung cho mọi Model trong dự án
     * * @param inputStream Luồng file tải lên
     *
     * @param rowMapper     Hàm dùng để bóc tách 1 dòng (Row) thành Object (T)
     * @param errorMessages Danh sách hứng lỗi để trả về giao diện
     * @return Danh sách các đối tượng hợp lệ
     */
    public static <T> List<T> readExcel(InputStream inputStream, Function<Row, T> rowMapper, List<String> errorMessages) throws IOException {
        List<T> validItems = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
            Iterator<Row> rowIterator = sheet.iterator();

            // 1. Bỏ qua dòng tiêu đề
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            // 2. Lặp qua từng dòng dữ liệu
            while (rowIterator.hasNext()) {
                Row currentRow = rowIterator.next();

                // Bỏ qua nếu dòng rỗng (cột đầu tiên trống)
                if (currentRow.getCell(0) == null || currentRow.getCell(0).toString().trim().isEmpty()) {
                    continue;
                }

                try {
                    // 3. ĐIỂM SÁNG GIÁ NHẤT: Gọi hàm Parser được truyền từ bên ngoài vào
                    T item = rowMapper.apply(currentRow);
                    validItems.add(item);
                } catch (IllegalArgumentException e) {
                    // Thu thập lỗi
                    int rowNum = currentRow.getRowNum() + 1;
                    errorMessages.add("Dòng " + rowNum + ": " + e.getMessage());
                }
            }
        }
        return validItems;
    }
}
