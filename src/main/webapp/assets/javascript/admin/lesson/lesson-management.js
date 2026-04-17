$(document).ready( function () {
    let debounceTimer;

// Lắng nghe toàn bộ thay đổi trên form lọc
    $('#filterForm input, #filterForm select').on('input change', function() {
        clearTimeout(debounceTimer);

        debounceTimer = setTimeout(() => {
            //Lấy dữ liệu từ form
            let formData = $('#filterForm').serialize();

            //Thêm tham số để yêu cầu lấy dữ liệu mới
            //nhưng chỉ truyền vào bảng chứ không load lại toàn bộ trang
            formData += "&renderType=partial";

            // Gửi AJAX để lấy dữ liệu mới
            $.ajax({
                url: 'admin/lessons', // URL Servlet của bạn
                type: 'GET',
                data: formData,
                beforeSend: function() {
                    // Làm mờ bảng một chút để báo hiệu đang load
                    $('#lessonTableBody').css('opacity', '0.5');
                },
                success: function (html) {
                    // Cập nhật HTML và hiện rõ lại
                    $('#lessonTableBody').html(html).css('opacity', '1');
                },
                error: function(xhr) {
                    console.error("Lỗi AJAX:", xhr.responseText);
                }
            });
        }, 500); // 500ms debounce
    })

    $('#filterForm').on('submit', function(e) { e.preventDefault(); });
});
