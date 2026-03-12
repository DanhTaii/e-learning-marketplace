document.addEventListener('DOMContentLoaded', function () {
    const lessonItems = document.querySelectorAll('.lesson-item');
    const videoPlayer = document.getElementById('mainVideoPlayer');
    const titleDisplay = document.getElementById('mainLessonTitle');
    const placeholder = document.getElementById('videoPlaceholder');

    lessonItems.forEach(item => {
        item.addEventListener('click', function () {
            const rawUrl = this.getAttribute('data-video-url');
            const lessonTitle = this.getAttribute('data-title');

            let finalUrl = formatYoutubeUrl(rawUrl);

            if (finalUrl) {
                // Hiển thị Video
                if (videoPlayer) {
                    videoPlayer.src = finalUrl;
                    videoPlayer.style.display = 'block';
                }
                if (placeholder) {
                    placeholder.style.display = 'none';
                }
            } else {
                // Hiển thị Placeholder (Thông báo không có video)
                if (videoPlayer) {
                    videoPlayer.style.display = 'none';
                    videoPlayer.src = ""; // Xóa src cũ để tránh tiếng video vẫn phát ngầm
                }
                if (placeholder) {
                    placeholder.style.display = 'flex';
                }
            }

            titleDisplay.innerText = lessonTitle + (finalUrl ? "" : " (Chưa có video)");

            // Hiệu ứng Active
            lessonItems.forEach(li => li.style.background = 'var(--dark-blue)');
            this.style.background = '#3d5a80';
        });
    });

    if (lessonItems.length > 0) {
        lessonItems[0].click();
    }
});

function formatYoutubeUrl(url) {
    if (!url || url === "null" || url.trim() === "") return null;

    // Kiểm tra xem nó có thực sự là link Youtube không
    const isYoutube = url.includes('youtube.com') || url.includes('youtu.be');

    if (isYoutube) {
        if (url.includes('watch?v=')) {
            return url.replace('watch?v=', 'embed/').split('&')[0];
        } else if (url.includes('youtu.be/')) {
            return url.replace('youtu.be/', 'www.youtube.com/embed/');
        }
        return url; // Nếu là link embed sẵn
    }

    // Nếu không phải link Youtube, coi như không có video và trả về null
    return null;
}
//Tránh việc khi bấm vào checkbox mà nó cũng chuyển sang video đó
const checkboxes = document.querySelectorAll('.lesson-checkbox');

checkboxes.forEach(checkbox => {
    checkbox.addEventListener('click', function (event) {
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
            if (data.status === 'success') {
                console.log('Cập nhật tiến độ thành công!');
            }
        })
        .catch(error => console.error('Lỗi:', error));
}