    $(document).ready(function () {
    Validator.setupAutoClearErrors();
    $('#myForm').on('submit', function (e) {
    let oldPass = $('#oldPass').val().trim();
    let reType = $('#reNewPass').val().trim();
    let isValid = true;

    let newPassError = Validator.checkPassword(oldPass);
    if (newPassError) {
    $('#error_oldPass').text(newPassError);
    isValid = false;
}

    if (oldPass !== reType) {
    $('#error_reNewPass').text('Mật khẩu nhập lại không khớp');
    isValid = false;
}

    if (!isValid) {
    e.preventDefault();
}
    return isValid;
});

});
