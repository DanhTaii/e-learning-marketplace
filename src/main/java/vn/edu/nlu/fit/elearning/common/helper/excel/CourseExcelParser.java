package vn.edu.nlu.fit.elearning.common.helper.excel;

import org.apache.poi.ss.usermodel.Row;
import vn.edu.nlu.fit.elearning.common.helper.enums.Level;
import vn.edu.nlu.fit.elearning.common.helper.validator.course.CourseValidator;
import vn.edu.nlu.fit.elearning.common.utils.excel.ExcelCellUtils;
import vn.edu.nlu.fit.elearning.feature.course.common.model.Course;

import java.util.Map;

public class CourseExcelParser {
    public static Course parseRowToCourse(Row row) throws IllegalArgumentException {
        Course course = new Course();

        // Cứ gọi Utils ra lấy dữ liệu, KHÔNG CẦN try-catch dài dòng nữa!
        course.setTitle(ExcelCellUtils.getString(row, 0));
        course.setSubtitle(ExcelCellUtils.getString(row, 1));
        course.setPrice(ExcelCellUtils.getInt(row, 2));            // Tự động ép kiểu int
        course.setDiscountPrice(ExcelCellUtils.getInt(row, 3));

        String levelStr = ExcelCellUtils.getString(row, 4);
        course.setLevel(levelStr != null ? Level.valueOf(levelStr.toUpperCase()) : Level.BEGINNER);

        course.setCategoryId(ExcelCellUtils.getInt(row, 5));
        course.setIsPublic(ExcelCellUtils.getBoolean(row, 6));     // Tự động ép kiểu boolean

        // Sau đó gọi Validator để chốt chặn cuối cùng...
        Map<String, String> errors = CourseValidator.validate(course);

        if (!errors.isEmpty()) {
            StringBuilder errorMsg = new StringBuilder();
            errors.forEach((field, msg) -> errorMsg.append(msg).append("; "));
            throw new IllegalArgumentException(errorMsg.toString());
        }

        return course;
    }
}
