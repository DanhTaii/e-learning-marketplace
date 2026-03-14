$(document).ready(function () {
    Validator.setupAutoClearErrors();

    $('#myForm').on('submit', function (e) {
        let email = $('#login_email').val().trim();
        let pass = $('#pass').val().trim();
        let isValid = true;

        let emailError = Validator.checkEmail(email);
        if (emailError) {
            $('#error_email').text(emailError);
            isValid = false;
        }


        if (pass === '') {
            $('#error_pass').text('Vui lòng nhập mật khẩu');
            isValid = false;
        }

        if (!isValid) {
            e.preventDefault();
        }
        return isValid;
    });

});

$(document).ready(function () {
    const togglePassword = $('#togglePassword');
    const passwordInput = $('#pass');

    togglePassword.on('click', function () {
        const type = passwordInput.attr('type') === 'password' ? 'text' : 'password';
        passwordInput.attr('type', type);

        this.classList.toggle('fa-eye-slash');
        this.classList.toggle('fa-eye');
    });
})