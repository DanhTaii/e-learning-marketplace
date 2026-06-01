const Validator = {
    checkPassword: function (password) {
        if (!password) return "Mật khẩu không được để trống";
        if (password.length < 8) return "Mật khẩu phải có ít nhất 8 ký tự";
        if (password.length > 25) return "Mật khẩu không được quá 25 ký tự";
        if (!/[A-Z]/.test(password)) return "Mật khẩu phải chứa ít nhất 1 chữ cái viết hoa";
        if (!/[a-z]/.test(password)) return "Mật khẩu phải chứa ít nhất 1 chữ cái viết thường";
        if (!/[0-9]/.test(password)) return "Mật khẩu phải chứa ít nhất 1 chữ số";
        if (!/[^A-Za-z0-9]/.test(password)) return "Mật khẩu phải chứa ít nhất 1 ký tự đặc biệt";
        return null;
    },
    checkFullName: function (fullName) {
        if (!fullName) {
            return "Vui lòng nhập họ tên";
        }

        fullName = fullName.trim();

        if (fullName.length < 2) {
            return "Họ tên quá ngắn";
        }

        if (fullName.length > 100) {
            return "Họ tên quá dài";
        }

        const regex = /^[\p{L}\s]+$/u;
        if (!regex.test(fullName)) {
            return "Họ tên chỉ được chứa chữ cái và khoảng trắng";
        }

        return null;
    },

    checkEmail: function (email) {
        if (!email) return "Vui lòng nhập email";

        // 2. Định dạng Regex (Chuẩn và phổ biến nhất)
        // Cấu trúc: [Tên] @ [Tên miền] . [Phần mở rộng]
        const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

        if (!emailRegex.test(email)) {
            return "Định dạng email không hợp lệ (ví dụ: abc@gmail.com)";
        }

        if (email.length > 255) {
            return "Email quá dài, vui lòng kiểm tra lại";
        }

        return null;
    },

    checkPhone: function (phone) {
        if (!phone) {
            return "Vui lòng nhập số điện thoại";
        }

        if (!/^\d{10,11}$/.test(phone)) {
            return "Số điện thoại phải có 10 hoặc 11 chữ số";
        }

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