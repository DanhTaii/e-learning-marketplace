package vn.edu.nlu.fit.elearning.common.helper.validator.tag;

import vn.edu.nlu.fit.elearning.common.utils.validation.ValidationUtils;
import vn.edu.nlu.fit.elearning.feature.tag.model.Tag;

import java.util.HashMap;
import java.util.Map;

public class TagValidator {

    public static Map<String, String> validate(Tag tag) {
        Map<String, String> errors = new HashMap<>();

        if (ValidationUtils.isEmpty(tag.getName())) {
            errors.put("nameTag", "Tên tag không được để trống!");
        } else {
            String lengthErr = ValidationUtils.checkLength(tag.getName(), "Tên tag", 3, 255);
            if (lengthErr != null) {
                errors.put("nameTag", lengthErr);
            }
        }

        if (ValidationUtils.isEmpty(tag.getSlug())) {
            errors.put("slug", "Slug không được để trống!");
        } else {
            String slugErr = ValidationUtils.checkLength(tag.getSlug(), "Slug", 3, 255);
            if (slugErr != null) {
                errors.put("slug", slugErr);
            }
        }

        if (tag.getStatus() == null) {
            errors.put("status", "Trạng thái không hợp lệ!");
        }

        return errors;
    }
}