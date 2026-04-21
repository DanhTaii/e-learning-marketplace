$(document).ready(function () {

    $('#categoryTitle').on('input', function () {
        const title = $(this).val();

        const slug = title
            .toLowerCase()
            .trim()
            .normalize("NFD").replace(/[\u0300-\u036f]/g, "") // bỏ dấu tiếng Việt
            .replace(/đ/g, 'd')
            .replace(/[^a-z0-9\s-]/g, '') // bỏ ký tự đặc biệt
            .replace(/\s+/g, '-')         // space → -
            .replace(/-+/g, '-');

        $('#categorySlug').val(slug);
    });

});