$(document).ready(function () {
    const initialName = $('#user_name').val().trim();
    const initialPhone = $('#user_phone').val().trim();

    Validator.setupAutoClearErrors();

    $('#myForm').on('submit', function (e) {
        let name = $('#user_name').val().trim();
        let phone = $('#user_phone').val().trim();
        let isValid = true;

        let usernameError = Validator.checkUsername(name);
        if (usernameError) {
            $('#error_username').text(usernameError);
            isValid = false;
        }

        let phoneError = Validator.checkPhone(phone);
        if (phoneError) {
            $('#error_phone').text(phoneError);
            isValid = false;
        }

        if (!isValid) {
            e.preventDefault();
        }
        return isValid;
    });

});
