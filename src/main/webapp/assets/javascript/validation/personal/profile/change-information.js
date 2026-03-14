    $(document).ready(function () {
    const initialName = $('#user_name').val().trim();
    const initialPhone = $('#user_phone').val().trim();
    const initialUrl = $('#user_url').val().trim();

    Validator.setupAutoClearErrors();

    $('#myForm').on('submit', function (e) {
    let name = $('#user_name').val().trim();
    let phone = $('#user_phone').val().trim();
    let url = $('#user_url').val().trim();
    let isValid = true;
    if (name === initialName && phone === initialPhone && url === initialUrl) {
    e.preventDefault();
    alert("Bạn chưa thay đổi thông tin nào!");
    return false;
}

    let usernameError = Validator.checkUsername(name);
    if (usernameError) {
    $('#error_username').text(usernameError);
    isValid = false;
}
    if(url === ""){
    $('#error_url').text("Bạn không được bỏ trống!");
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
