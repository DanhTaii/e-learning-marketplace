package vn.edu.nlu.fit.elearning.common.helper.validator.course;

import vn.edu.nlu.fit.elearning.common.utils.validation.ValidationUtils;
import vn.edu.nlu.fit.elearning.feature.course.common.model.Course;

import java.util.HashMap;
import java.util.Map;

public class CourseValidator {
    public static Map<String, String> validate(Course course) {
        Map<String, String> errors = new HashMap<>();

        // Tên khóa học
        String titleError = ValidationUtils.checkLength(course.getTitle(), "Tên khóa học", 10, 150);
        if (titleError != null) {
            errors.put("courseTitle", titleError);
        }

        // Phụ đề
        String subtitleError = ValidationUtils.checkLength(course.getSubtitle(), "Phụ đề", 10, 250);
        if (subtitleError != null) {
            errors.put("courseSubtitle", subtitleError);
        }

//        // Mục tiêu
//        String goalsError = ValidationUtils.checkLength(course.getGoals(), "Mục tiêu", 20, 1000);
//        if (goalsError != null) {
//            errors.put("courseGoals", goalsError);
//        }
//
//        // Mô tả
//        String descriptionError = ValidationUtils.checkLength(course.getDescription(), "Mô tả", 50, 5000);
//        if (descriptionError != null) {
//            errors.put("courseDescription", descriptionError);
//        }

        // Giá gốc
        if (course.getPrice() <= 0) {
            errors.put("coursePrice", "Giá gốc phải lớn hơn 0");
        }

        // Giá giảm
        if (course.getDiscountPrice() < 0) {
            errors.put("courseDiscountPrice", "Giá giảm phải là số dương");
        } else if (course.getDiscountPrice() > course.getPrice()) {
            errors.put("courseDiscountPrice",
                    "Giá giảm không được lớn hơn giá gốc");
        }

        // Danh mục
        if (course.getCategoryId() <= 0) {
            errors.put("courseCategory",
                    "Vui lòng chọn danh mục");
        }

        // Level
        if (course.getLevel() == null) {
            errors.put("courseLevel",
                    "Cấp độ khóa học không hợp lệ");
        }

//        // Thumbnail
//        if (ValidationUtils.isEmpty(course.getThumbnailUrl())) {
//            errors.put("courseThumbnail",
//                    "Vui lòng chọn ảnh đại diện");
//        }

        return errors;
    }
}
