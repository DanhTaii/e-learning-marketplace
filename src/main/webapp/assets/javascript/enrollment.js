document.addEventListener('DOMContentLoaded', function() {
    const lessonItems = document.querySelectorAll('.lesson-item'); // Bạn cần thêm class 'lesson-item' vào thẻ div bài học
    const videoPlayer = document.getElementById('mainVideoPlayer');
    const titleDisplay = document.getElementById('mainLessonTitle');

    lessonItems.forEach(item => {
        item.addEventListener('click', function() {
            // Lấy dữ liệu từ thuộc tính data-
            const rawUrl = this.getAttribute('data-video-url');
            const lessonTitle = this.getAttribute('data-title');

            // Hàm xử lý chuyển đổi link YouTube sang dạng Embed
            let finalUrl = rawUrl;
            if (rawUrl.includes('watch?v=')) {
                finalUrl = rawUrl.replace('watch?v=', 'embed/');
                // Xử lý nếu có thêm tham số phụ như &t=...
                finalUrl = finalUrl.split('&')[0];
            } else if (rawUrl.includes('youtu.be/')) {
                finalUrl = rawUrl.replace('youtu.be/', 'www.youtube.com/embed/');
            }

            //Cập nhật vào Iframe và Tiêu đề
            if (finalUrl) {
                videoPlayer.src = finalUrl;
                titleDisplay.innerText = lessonTitle;
            }

            //Hiệu ứng Active (Đổi màu bài đang chọn)
            lessonItems.forEach(li => li.style.background = 'var(--dark-blue)');
            this.style.background = '#3d5a80';
        });
    });

    // Tự động click vào bài đầu tiên khi load trang
    if(lessonItems.length > 0) {
        lessonItems[0].click();
    }
});