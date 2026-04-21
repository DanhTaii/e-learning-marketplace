$('#categoryForm').on('submit', function (e) {

    let isValid = true;

    const title = $('#categoryTitle').val().trim();
    const slug = $('#categorySlug').val().trim();
    const parentId = $('#parentId').val().trim();

    $('#error_categoryTitle').text('');
    $('#error_slug').text('');
    $('#error_parentId').text('');

    if (!title) {
        $('#error_categoryTitle').text("Tên danh mục không được để trống!");
        isValid = false;
    }

    if (!slug) {
        $('#error_slug').text("Slug không được để trống!");
        isValid = false;
    }

    if (parentId !== "") {
        const val = parseInt(parentId);
        if (isNaN(val) || val < 0) {
            $('#error_parentId').text("Parent ID phải >= 0");
            isValid = false;
        }
    }

    if (!isValid) {
        e.preventDefault();
        return false;
    }

    return true;
});