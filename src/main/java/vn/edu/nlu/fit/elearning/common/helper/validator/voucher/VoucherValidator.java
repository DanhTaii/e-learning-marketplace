package vn.edu.nlu.fit.elearning.common.helper.validator.voucher;

import vn.edu.nlu.fit.elearning.common.utils.validation.ValidationUtils;
import vn.edu.nlu.fit.elearning.feature.voucher.model.Voucher;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class VoucherValidator {
        public static Map<String, String> validate(Voucher voucher) {
            Map<String, String> errors = new HashMap<>();

            // 1. Kiểm tra Mã Voucher (code)
            if (ValidationUtils.isEmpty(voucher.getCode())) {
                errors.put("code", "Mã Voucher không được để trống!");
            }
            if (ValidationUtils.checkLength(voucher.getCode(), "Mã Voucher", 3, 50) != null) {
                errors.put("code", ValidationUtils.checkLength(voucher.getCode(), "Mã Voucher", 3, 50));
            }

            // 2. Kiểm tra Tên chương trình (title)
            if (ValidationUtils.isEmpty(voucher.getTitle())) {
                errors.put("title", "Tên chương trình giảm giá không được để trống!");
            }
            if (ValidationUtils.checkLength(voucher.getTitle(), "Tên chương trình", 5, 255) != null) {
                errors.put("title", ValidationUtils.checkLength(voucher.getTitle(), "Tên chương trình", 5, 255));
            }

            if (voucher.getDiscountValue() <= 0) {
                errors.put("discountValue", "Mức giảm giá phải lớn hơn 0!");
            } else if ("PERCENT".equals(voucher.getDiscountType()) && voucher.getDiscountValue() > 100) {
                errors.put("discountValue", "Mức giảm theo phần trăm không được vượt quá 100%!");
            }

            // 2. Kiểm tra Giá trị đơn hàng tối thiểu (minOrderValue)
            if (voucher.getMinOrderValue() < 0) {
                errors.put("minOrderValue", "Giá trị đơn hàng tối thiểu không được là số âm!");
            }

            // 3. Kiểm tra Mức giảm tối đa (maxDiscountValue)
            if ("PERCENT".equals(voucher.getDiscountType()) && voucher.getMaxDiscountValue() != null) {
                if (voucher.getMaxDiscountValue() < 0) {
                    errors.put("maxDiscountValue", "Mức giảm tối đa không được là số âm!");
                }
            }

            // 6. Kiểm tra Giới hạn lượt sử dụng (usageLimit)
            if (voucher.getUsageLimit() != null && voucher.getUsageLimit() <= 0) {
                errors.put("usageLimit", "Tổng số lượt sử dụng phải lớn hơn 0!");
            }

            // 7. Kiểm tra Thời gian áp dụng (startDate & endDate)
            if (voucher.getStartDate() == null) {
                errors.put("startDate", "Vui lòng chọn ngày bắt đầu!");
            }
            if (voucher.getEndDate() == null) {
                errors.put("endDate", "Vui lòng chọn ngày kết thúc!");
            }

            // Kiểm tra logic thời gian kết thúc phải sau thời gian bắt đầu
            if (voucher.getStartDate() != null && voucher.getEndDate() != null) {
                if (voucher.getEndDate().before(voucher.getStartDate())) {
                    errors.put("endDate", "Thời gian kết thúc phải sau thời gian bắt đầu!");
                }
            }

            return errors;
        }
    }