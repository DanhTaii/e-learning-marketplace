package vn.edu.nlu.fit.elearning.common.utils.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

public class ExcelCellUtils {
    private static final DataFormatter FORMATTER = new DataFormatter();

    // Hàm lấy chuỗi an toàn
    public static String getString(Row row, int colIndex) {
        Cell cell = row.getCell(colIndex);
        if (cell == null) return null;
        String val = FORMATTER.formatCellValue(cell).trim();
        return val.isEmpty() ? null : val;
    }

    // Hàm lấy số nguyên an toàn (Tự động bắt lỗi)
    public static int getInt(Row row, int colIndex) {
        String str = getString(row, colIndex);
        if (str == null) return 0;
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Cột " + (colIndex + 1) + " phải là số!");
        }
    }

    // Hàm lấy Boolean
    public static boolean getBoolean(Row row, int colIndex) {
        String str = getString(row, colIndex);
        return str != null && (str.equalsIgnoreCase("true") || str.equals("1"));
    }

}
