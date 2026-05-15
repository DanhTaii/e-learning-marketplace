// ========================= TẠO NOTE MỚI ========================

document.addEventListener('DOMContentLoaded', function () {
    const btnSaveNote = document.getElementById('btn-save-note')
    const cloudinaryPlayer = document.getElementById('cloudinaryPlayer')
    const currentTime = document.getElementById('current-video-time-display');
    const noteInput = document.getElementById('note-content-input');

    // LIÊN TỤC CẬP NHẬT THỜI GIAN
    if (cloudinaryPlayer) {
        cloudinaryPlayer.addEventListener('timeupdate', function () {
            const currentSeconds = Math.floor(cloudinaryPlayer.currentTime);
            const minutes = Math.floor(currentSeconds / 60).toString().padStart(2, '0')
            const seconds = (currentSeconds % 60).toString().padStart(2, '0')

            if (currentTime) {
                currentTime.innerText = `${minutes}:${seconds}`
            }
        })
    }

    if (btnSaveNote) {
        btnSaveNote.addEventListener('click', function () {
            const content = noteInput.value.trim();
            const currentLessonId = PlayerState.currentLessonId

            // Lấy số giây hiện tại
            const noteTime = Math.floor(cloudinaryPlayer.currentTime);

            // Vô hiệu hóa nút bấm
            btnSaveNote.innerHTML = '<i class="fa-solid fa-spinner fa-spin"></i> Đang lưu...';
            btnSaveNote.disabled = true;

            fetch('personal/my-course/note', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: `lessonId=${currentLessonId}&noteTime=${noteTime}&content=${encodeURIComponent(content)}`
            })
                .then(response => {
                    return response.json()
                })
                .then(data => {
                    if (data.status === 'success') {
                        // Thành công: Xóa trắng ô nhập liệu và báo thành công
                        noteInput.value = "";
                        console.log("Đã lưu ghi chú thành công tại giây: " + noteTime);

                    } else {
                        alert("Có lỗi xảy ra, không thể lưu ghi chú!");
                    }
                })
                .catch(error => {
                    console.error('Lỗi khi lưu ghi chú:', error);
                    alert("Lỗi kết nối mạng!");
                })
                .finally(() => {
                    // Phục hồi lại trạng thái ban đầu của nút Lưu
                    btnSaveNote.innerHTML = '<i class="fa-regular fa-floppy-disk"></i> Lưu ghi chú';
                    btnSaveNote.disabled = false;
                });
        })
    }

})