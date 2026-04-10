package vn.edu.nlu.fit.elearning.common.helper.validator.lesson;

import vn.edu.nlu.fit.elearning.common.utils.validation.ValidationUtils;
import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;

import java.util.HashMap;
import java.util.Map;

public class LessonValidator {
    public static Map<String, String> validate(Lesson lesson) {
        Map<String, String> errors = new HashMap<>();
        if (lesson.getCourseId() <= 0) {
            errors.put("idCourse", "Vui lòng chọn một khóa học cụ thể!");
        }

        if (ValidationUtils.isEmpty(lesson.getTitle())) {
            errors.put("nameLesson", "Tên bài học không được để trống!");
        }

        if(ValidationUtils.checkLength(lesson.getTitle(), "Tên bài học", 5, 255) != null) {
            errors.put("nameLesson", ValidationUtils.checkLength(lesson.getTitle(), "Tên bài học", 5, 255));
        }

        if (lesson.getDurationMinutes() <= 0) {
            errors.put("durationMinutes", "Thời lượng phải lớn hơn 0!");
        }

        if (lesson.getOrderIndex() <= 0) {
            errors.put("orderIndex", "Thứ tự phải lớn hơn 0!");
        }

        if (ValidationUtils.isEmpty(lesson.getVideoUrl())) {
            errors.put("urlVideo", "Đường dẫn video không được để trống!");
        }
        return errors;
    }
}
