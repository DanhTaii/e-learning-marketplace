$(document).ready(function () {

    Validator.setupAutoClearErrors();

    function clearError(id) {
        $(id).text('');
    }

    function setError(id, message) {
        $(id).text(message);
    }

    $('#myForm').on('submit', function (e) {

        let newPass = $('#newPass').val().trim();
        let reType = $('#reNewPass').val().trim();
        let email = $('#login_email').val().trim();
        let fullName = $('#fullName').val().trim();

        let isValid = true;

        let fullNameError = Validator.checkFullName(fullName);
        if (fullNameError) {
            setError('#error_fullName', fullNameError);
            isValid = false;
        } else {
            clearError('#error_fullName');
        }


        let emailError = Validator.checkEmail(email);
        if (emailError) {
            setError('#error_email', emailError);
            isValid = false;
        } else {
            clearError('#error_email');
        }

        let newPassError = Validator.checkPassword(newPass);
        if (newPassError) {
            setError('#error_newPass', newPassError);
            isValid = false;
        } else {
            clearError('#error_newPass');
        }

        if (!reType) {
            setError('#error_reNewPass', 'Vui lòng nhập lại mật khẩu');
            isValid = false;
        } else if (newPass !== reType) {
            setError('#error_reNewPass', 'Mật khẩu nhập lại không khớp');
            isValid = false;
        } else {
            clearError('#error_reNewPass');
        }

        if (!isValid) {
            e.preventDefault();
        }

        return isValid;
    });

    $('#fullName').on('input', function () {
        let val = $(this).val().trim();
        let err = Validator.checkFullName(val);

        err ? setError('#error_fullName', err) : clearError('#error_fullName');
    });

    $('#login_email').on('input', function () {
        let val = $(this).val().trim();
        let err = Validator.checkEmail(val);
        err ? setError('#error_email', err) : clearError('#error_email');
    });

    $('#newPass').on('input', function () {
        let val = $(this).val().trim();
        let err = Validator.checkPassword(val);
        err ? setError('#error_newPass', err) : clearError('#error_newPass');
    });

    $('#reNewPass').on('input', function () {
        let newPass = $('#newPass').val().trim();
        let reType = $(this).val().trim();

        if (!reType) {
            setError('#error_reNewPass', 'Vui lòng nhập lại mật khẩu');
        } else if (newPass !== reType) {
            setError('#error_reNewPass', 'Mật khẩu nhập lại không khớp');
        } else {
            clearError('#error_reNewPass');
        }
    });

});
$(document).ready(function () {
    // Xử lý ẩn/hiện mật khẩu
    $('.toggle-password').on('click', function () {
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