const Validator = {
    checkRequired: function (value, message) {
        return value.trim() === '' ? message : null;
    },

    checkPrice: function (price) {
        if (!price) return "Vui lòng nhập giá khóa học";
        if (isNaN(price) || price < 0) return "Giá tiền không hợp lệ";
        return null;
    },

    checkDiscount: function (price, discount) {
        if (isNaN(discount) || discount < 0) return "Giá tiền không hợp lệ";
        if (discount && parseInt(discount) >= parseInt(price)) {
            return "Giá giảm phải nhỏ hơn giá gốc";
        }
        return null;
    },

    checkLength: function (value, min, max, label) {
            const length = value.trim().length;
            if (length === 0) return `Vui lòng nhập <c:out value="${label}"/>`;
            if (length < min) return `${label} phải có ít nhất ${min} ký tự`;
            if (length > max) return `${label} không được vượt quá ${max} ký tự`;
            return null;
    },

    setupAutoClearErrors: function () {
        $('input, select, textarea').on('input change', function () {
            let inputId = $(this).attr('id');
            if (inputId) {
                $('#error_' + inputId).text('');
            }
        });
    }
};