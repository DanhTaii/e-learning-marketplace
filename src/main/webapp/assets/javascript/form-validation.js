const Validator = {
    checkPassword: function (password) {
        if (!password) return "Mật khẩu không được để trống";
        if (password.length < 8) return "Mật khẩu phải có ít nhất 8 ký tự";
        if (!/[A-Z]/.test(password)) return "Mật khẩu phải chứa ít nhất 1 chữ cái viết hoa";
        if (!/[a-z]/.test(password)) return "Mật khẩu phải chứa ít nhất 1 chữ cái viết thường";
        if (!/[0-9]/.test(password)) return "Mật khẩu phải chứa ít nhất 1 chữ số";
        if (!/[^A-Za-z0-9]/.test(password)) return "Mật khẩu phải chứa ít nhất 1 ký tự đặc biệt";
        return null;
    },
    checkUsername: function (username) {
        if (!username) return "Vui lòng nhập tên đăng nhập";
        if (username.length < 3) return "Tên đăng nhập phải có ít nhất 3 ký tự";
        if (!/^[a-zA-Z0-9]+$/.test(username)) {
            return "Tên đăng nhập không được chứa ký tự đặc biệt, khoảng trắng hoặc dấu tiếng Việt";
        }
        return null;
    },

    checkEmail: function (email) {
        if (!email) return "Vui lòng nhập email";
        return null;
    },

    checkPhone: function (phone){
        if (!phone) return "Vui lòng nhập số điện thoại";
        const regex = /^\d{10,11}$/;

        if (!/^[a-zA-Z0-9]+$/.test(phone)) {
            return "Tên đăng nhập không được chứa ký tự đặc biệt, khoảng trắng hoặc dấu tiếng Việt";
        }
        if (!regex.test(phone)) {
            return "Số điện thoại phải có 10 hoặc 11 chữ số";
        }
        return null;
    },

    setupAutoClearErrors: function () {
        $('input').on('input', function () {
            $(this).next('.error-client').text('');

            let inputId = $(this).attr('id');
            if(inputId) {
                $('#error_' + inputId).text('');
            }
        });
    }
};