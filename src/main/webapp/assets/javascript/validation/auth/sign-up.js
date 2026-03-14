$(document).ready(function () {
    Validator.setupAutoClearErrors();
    $('#myForm').on('submit', function (e) {
        let newPass = $('#newPass').val().trim();
        let reType = $('#reNewPass').val().trim();
        let email = $('#login_email').val().trim();
        let username = $('#name').val().trim();
        let isValid = true;


        let usernameError = Validator.checkUsername(username);
        if (usernameError) {
            $('#error_username').text(usernameError);
            isValid = false;
        }

        let emailError = Validator.checkEmail(email);
        if (emailError) {
            $('#error_email').text(emailError);
            isValid = false;
        }


        let newPassError = Validator.checkPassword(newPass);
        if (newPassError) {
            $('#error_newPass').text(newPassError);
            isValid = false;
        }

        if (newPass !== reType) {
            $('#error_reNewPass').text('Mật khẩu nhập lại không khớp');
            isValid = false;
        }

        if (!isValid) {
            e.preventDefault();
        }
        return isValid;
    });

});

$(document).ready(function () {
    // Xử lý ẩn/hiện mật khẩu
    $('.toggle-password').on('click', function() {
        // Lấy ID của ô input cần hiện mật khẩu từ attribute data-target
        const targetSelector = $(this).attr('data-target');
        const input = $(targetSelector);

        // Chuyển loại input
        const type = input.attr('type') === 'password' ? 'text' : 'password';
        input.attr('type', type);

        // Đổi icon
        $(this).toggleClass('fa-eye fa-eye-slash');
    });
});