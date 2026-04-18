package vn.edu.nlu.fit.elearning.common.helper.validator.category;

import vn.edu.nlu.fit.elearning.common.utils.validation.ValidationUtils;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;

import java.util.HashMap;
import java.util.Map;

public class CategoryValidator {

    public static Map<String, String> validate(Category category) {
        Map<String, String> errors = new HashMap<>();

        if (ValidationUtils.isEmpty(category.getName())) {
            errors.put("nameCategory", "Tên danh mục không được để trống!");
        } else {
            String lengthErr = ValidationUtils.checkLength(category.getName(), "Tên danh mục", 3, 255);
            if (lengthErr != null) {
                errors.put("nameCategory", lengthErr);
            }
        }

        if (ValidationUtils.isEmpty(category.getSlug())) {
            errors.put("slug", "Slug không được để trống!");
        } else {
            String slugErr = ValidationUtils.checkLength(category.getSlug(), "Slug", 3, 255);
            if (slugErr != null) {
                errors.put("slug", slugErr);
            }
        }

        if (category.getParentId() < 0) {
            errors.put("parentId", "Parent ID không hợp lệ!");
        }

        if (category.getStatus() == null) {
            errors.put("status", "Trạng thái không hợp lệ!");
        }

        return errors;
    }
}