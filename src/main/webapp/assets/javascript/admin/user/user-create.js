$(document).ready(function () {

    $('#fullName').on('input', function () {
        const fullName = $(this).val();
        const username = fullName
            .toLowerCase()
            .trim()
            .normalize("NFD")
            .replace(/[\u0300-\u036f]/g, "") // bỏ dấu
            .replace(/đ/g, 'd')
            .replace(/[^a-z0-9\s]/g, '') // bỏ ký tự đặc biệt
            .replace(/\s+/g, '.') // space -> .
            .replace(/\.+/g, '.');

        $('#username').val(username);
    });

});