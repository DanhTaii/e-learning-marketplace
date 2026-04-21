$('#tagForm').on('submit', function (e) {

    let isValid = true;

    const title = $('#tagTitle').val().trim();
    const slug = $('#tagSlug').val().trim();

    $('#error_tagTitle').text('');
    $('#error_slug').text('');

    if (!title) {
        $('#error_tagTitle').text("Tên thẻ không được để trống!");
        isValid = false;
    }

    if (!slug) {
        $('#error_slug').text("Slug không được để trống!");
        isValid = false;
    }

    if (!isValid) {
        e.preventDefault();
        return false;
    }

    return true;
});