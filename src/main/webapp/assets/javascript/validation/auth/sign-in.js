$(document).ready(function () {

    Validator.setupAutoClearErrors();

    function setError(id, message) {
        $(id).text(message);
    }

    function clearError(id) {
        $(id).text('');
    }

    $('#myForm').on('submit', function (e) {

        let email = $('#login_email').val().trim();
        let pass = $('#pass').val().trim();

        let isValid = true;

        // EMAIL
        let emailError = Validator.checkEmail(email);
        if (emailError) {
            setError('#error_email', emailError);
            isValid = false;
        } else {
            clearError('#error_email');
        }

        // PASSWORD
        if (!pass) {
            setError('#error_pass', 'Vui lòng nhập mật khẩu');
            isValid = false;
        } else {
            clearError('#error_pass');
        }

        if (!isValid) {
            e.preventDefault();
        }

        return isValid;
    });

    // realtime UX
    $('#login_email').on('input', function () {
        let val = $(this).val().trim();
        let err = Validator.checkEmail(val);
        err ? setError('#error_email', err) : clearError('#error_email');
    });

    $('#pass').on('input', function () {
        let val = $(this).val().trim();
        val ? clearError('#error_pass') : setError('#error_pass', 'Vui lòng nhập mật khẩu');
    });

    // toggle password
    $('#togglePassword').on('click', function () {
        const input = $('#pass');
        const type = input.attr('type') === 'password' ? 'text' : 'password';
        input.attr('type', type);

        $(this).toggleClass('fa-eye fa-eye-slash');
    });

});