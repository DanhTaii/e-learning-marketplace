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

//Tránh việc khi bấm vào checkbox mà nó cũng chuyển sang video đó
const checkboxes = document.querySelectorAll('.lesson-checkbox');

checkboxes.forEach(checkbox => {
    checkbox.addEventListener('click', function(event) {
        // NGĂN chặn sự kiện lan truyền lên thẻ div cha (.lesson-item)
        event.stopPropagation();

        //Như làm việc với bên Servlet
        const lessonId = this.getAttribute('data-lesson-id');
        const isCompleted = this.checked;

        // Gọi hàm xử lý AJAX ở đây
        updateProgress(lessonId, isCompleted);
    });
});

function updateProgress(lessonId, isCompleted) {
    fetch('my-course/detail', {
        // Gỉa lập 1 cái form để gửi nó xuống
        method: 'POST',
        headers: {
            //Dùng để nói với Server cái dữ liệu gửi trong Body có định dạng giống hệt như một cái Form HTML truyền thống
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `lessonId=${lessonId}&completed=${isCompleted}`
    })
        .then(response => response.json())
        .then(data => {
            if(data.status === 'success') {
                console.log('Cập nhật tiến độ thành công!');
            }
        })
        .catch(error => console.error('Lỗi:', error));
}