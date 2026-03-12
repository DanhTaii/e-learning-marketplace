const Validator = {
    // Kiểm tra trống chung cho các trường
    checkRequired: function (value, message) {
        return value.trim() === '' ? message : null;
    },

    // Kiểm tra giá tiền
    checkPrice: function (price) {
        if (!price) return "Vui lòng nhập giá khóa học";
        if (isNaN(price) || price < 0) return "Giá tiền không hợp lệ";
        return null;
    },

    // Kiểm tra giá giảm (không được lớn hơn giá gốc)
    checkDiscount: function (price, discount) {
        if (isNaN(discount) || discount < 0) return "Giá tiền không hợp lệ";
        if (discount && parseInt(discount) >= parseInt(price)) {
            return "Giá giảm phải nhỏ hơn giá gốc";
        }
        return null;
    },

    // Hàm kiểm tra độ dài linh hoạt
    checkLength: function (value, min, max, label) {
            const length = value.trim().length;
            if (length === 0) return `Vui lòng nhập ${label}`;
            if (length < min) return `${label} phải có ít nhất ${min} ký tự`;
            if (length > max) return `${label} không được vượt quá ${max} ký tự`;
            return null;
    },

    // Hàm xóa lỗi cũ khi đang gõ
    setupAutoClearErrors: function () {
        $('input, select, textarea').on('input change', function () {
            let inputId = $(this).attr('id');
            if (inputId) {
                $('#error_' + inputId).text('');
            }
        });
    }
};