function showLessonDetail(id) {
    console.log("Đã bấm nút Sửa cho Lesson ID:", id);

    // Lưu ý: Đảm bảo URL này khớp với Servlet trả về JSON của bạn
    fetch('admin/lesson/detail?id=' + id)
        .then(response => {
            if (!response.ok) throw new Error('Network response was not ok');
            return response.json();
        })
        .then(lesson => {
            console.log("Dữ liệu Lesson nhận về:", lesson);

            // 1. Cập nhật tiêu đề modal (dùng title hoặc nameLesson tùy theo object trả về)
            const title = lesson.title || lesson.nameLesson || "BÀI HỌC";
            document.getElementById('modal-title').innerText = "CẬP NHẬT: " + title.toUpperCase();

            // 2. Gán các giá trị cơ bản
            document.getElementById('detail-id').value = lesson.id;
            document.getElementById('detail-courseId').value = lesson.courseId;
            document.getElementById('old-courseId').value = lesson.courseId;// Tự động chọn option trong select
            document.getElementById('detail-nameLesson').value = lesson.title || lesson.nameLesson || "";
            document.getElementById('detail-videoURL').value = lesson.videoUrl || "";
            document.getElementById('detail-durationMinutes').value = lesson.durationMinutes || 0;

            // 3. Xử lý Số thứ tự (Quan trọng cho logic Re-order)
            const order = lesson.orderIndex || 0;
            document.getElementById('detail-orderIndex').value = order;
            document.getElementById('old-orderIndex').value = order; // Lưu lại index cũ

            // 4. Xử lý ngày tháng
            if (lesson.createdAt) {
                document.getElementById('detail-created').value = new Date(lesson.createdAt).toLocaleString('vi-VN');
            } else {
                document.getElementById('detail-created').value = "N/A";
            }

            if (lesson.updatedAt) {
                document.getElementById('detail-updated').value = new Date(lesson.updatedAt).toLocaleString('vi-VN');
            } else {
                document.getElementById('detail-updated').value = "Chưa có cập nhật";
            }

            // 5. HIỂN THỊ MODAL
            document.getElementById('lesson-detail').style.display = 'flex';
        })
        .catch(error => {
            console.error('Lỗi khi fetch dữ liệu lesson:', error);
            if (typeof toast === 'function') {
                toast({ title: 'Lỗi!', message: 'Không thể lấy thông tin bài học', type: 'error' });
            }
        });
}

// Hàm đóng modal (nhận ID để dùng chung cho nhiều loại modal)
function closeModal(modalId) {
    document.getElementById(modalId).style.display = 'none';
}

// Đóng khi click ra ngoài vùng modal
window.onclick = function (event) {
    let lessonModal = document.getElementById('lesson-detail');
    if (event.target == lessonModal) {
        closeModal('lesson-detail');
    }
}