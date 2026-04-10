const BaseValidator = {
    checkPositiveInteger: function (value, label) {
        if (!value) return `Vui lòng nhập ${label}`;
        if (!/^\d+$/.test(value)) return `${label} phải là số nguyên dương`;
        return null;
    },

    checkLength: function (value, min, max, label) {
        const length = value.trim().length;
        if (length === 0) return `Vui lòng nhập ${label}`;
        if (length < min) return `${label} phải có ít nhất ${min} ký tự`;
        if (length > max) return `${label} không được vượt quá ${max} ký tự`;
        return null;
    },

    setupAutoClearErrors: function () {
        $('input').on('input', function () {
            $(this).next('.error-client').text('');

            let inputId = $(this).attr('id');
            if (inputId) {
                $('#error_' + inputId).text('');
            }
        });
    }
};