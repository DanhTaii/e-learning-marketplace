$('#userForm').on('submit', function (e) {

    let isValid = true;

    const firstName = $('#firstName').val().trim();
    const lastName = $('#lastName').val().trim();
    const username = $('#username').val().trim();
    const email = $('#email').val().trim();
    const password = $('#password').val().trim();
    const confirmPassword = $('#confirmPassword').val().trim();

    // clear errors
    $('#error_username').text('');
    $('#error_email').text('');
    $('#error_password').text('');
    $('#error_confirmPassword').text('');
    $('#error_firstName').text('');
    $('#error_lastName').text('');

    if (!firstName) {
        $('#error_firstName').text('Họ không được để trống!');
        isValid = false;
    } else {

        // hỗ trợ tiếng Việt có dấu
        const fullNameRegex = /^[A-Za-zÀ-ỹ\s]+$/;

        if (!fullNameRegex.test(firstName)) {
            $('#error_firstName').text('Họ không hợp lệ!');
            isValid = false;
        }
    }

    if (!lastName) {
        $('#error_lastName').text('Tên không được để trống!');
        isValid = false;
    } else {

        // hỗ trợ tiếng Việt có dấu
        const fullNameRegex = /^[A-Za-zÀ-ỹ\s]+$/;

        if (!fullNameRegex.test(lastName)) {
            $('#error_lastName').text('Tên không hợp lệ!');
            isValid = false;
        }
    }

    // USERNAME
    if (!username) {

        $('#error_username').text('Tên người dùng không được để trống!');
        isValid = false;

    } else {

        if (username.length < 3 || username.length > 20) {

            $('#error_username').text('Tên người dùng phải từ 3 đến 20 ký tự!');
            isValid = false;

        } else if (!/^[a-zA-Z0-9._]+$/.test(username)) {

            $('#error_username').text('Chỉ được chứa chữ, số, . hoặc _');
            isValid = false;
        }
    }

    // EMAIL
    if (!email) {

        $('#error_email').text('Email không được để trống!');
        isValid = false;

    } else {

        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (!emailRegex.test(email)) {
            $('#error_email').text('Email không hợp lệ!');
            isValid = false;
        }
    }

    // PASSWORD
    if (!password) {

        $('#error_password').text('Mật khẩu không được để trống!');
        isValid = false;

    } else {

        if (password.length < 8 || password.length > 25) {

            $('#error_password').text('Mật khẩu phải từ 8 đến 25 ký tự!');
            isValid = false;

        } else if (!/[A-Z]/.test(password)) {

            $('#error_password').text('Phải có ít nhất 1 chữ hoa!');
            isValid = false;

        } else if (!/[a-z]/.test(password)) {

            $('#error_password').text('Phải có ít nhất 1 chữ thường!');
            isValid = false;

        } else if (!/\d/.test(password)) {

            $('#error_password').text('Phải có ít nhất 1 chữ số!');
            isValid = false;

        } else if (!/[!@#$%^&*(),.?":{}|<>_\-\[\]\\\/+=~`]/.test(password)) {

            $('#error_password').text('Phải có ít nhất 1 ký tự đặc biệt!');
            isValid = false;
        }
    }

    // CONFIRM PASSWORD
    if (!confirmPassword) {
        $('#error_confirmPassword').text('Xác nhận mật khẩu không được để trống!');
        isValid = false;

    } else if (password !== confirmPassword) {
        $('#error_confirmPassword').text('Mật khẩu xác nhận không khớp!');
        isValid = false;
    }

    if (!isValid) {
        e.preventDefault();
        return false;
    }

    return true;
});