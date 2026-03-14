$(document).ready(function () {


    Validator.setupAutoClearErrors();

    $('#myForm').on('submit', function (e) {
        let oldPass = $('#oldPass').val().trim();
        let newPass = $('#newPass').val().trim();
        let reType = $('#reNewPass').val().trim();
        let isValid = true;

        let oldPassError = Validator.checkPassword(oldPass);
        if (oldPassError) {
            $('#error_oldPass').text(oldPassError);
            isValid = false;
        }

        let newPassError = Validator.checkPassword(newPass);
        if (newPassError) {
            $('#error_newPass').text(newPassError);
            isValid = false;
        } else if (newPass === oldPass) {
            $('#error_newPass').text('Mật khẩu mới trùng mật khẩu cũ');
            isValid = false;
        }

        if (newPass !== reType) {
            $('#error_reType').text('Mật khẩu nhập lại không khớp');
            isValid = false;
        }

        if (!isValid) {
            e.preventDefault();
        }
        return isValid;
    });

});
