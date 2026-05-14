document.addEventListener('DOMContentLoaded', function () {
    const lessonItems = document.querySelectorAll('.lesson-item');
    const videoPlayer = document.getElementById('mainVideoPlayer');
    // const titleDisplay = document.getElementById('mainLessonTitle');
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

            // titleDisplay.innerText = lessonTitle + (finalUrl ? "" : " (Chưa có video)");

            // Hiệu ứng Active
            lessonItems.forEach(li => li.classList.remove('active-lesson'));
            this.classList.add('active-lesson');
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
        const enrollmentId = document.getElementById('enrollment-id').value;
        const courseId = document.getElementById("course-id").value;


        // Gọi hàm xử lý AJAX ở đây
        updateProgress(lessonId, isCompleted, enrollmentId, courseId);
    });
});

function updateProgress(lessonId, isCompleted, enrollmentId, courseId) {
    fetch('personal/my-course/detail', {
        // Gỉa lập 1 cái form để gửi nó xuống
        method: 'POST',
        headers: {
            //Dùng để nói với Server cái dữ liệu gửi trong Body có định dạng giống hệt như một cái Form HTML truyền thống
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `lessonId=${lessonId}&completed=${isCompleted}&enrollmentId=${enrollmentId}`
    })
        .then(response => response.json())
        .then(data => {
            if (data.status === 'success') {
                // console.log("THÀNH CÔNG")
                updateCircleProgress(data.newPercent)
                if (data.certId > 0) {
                    switchBtnGetCertificate(data.certId, courseId)
                }
            }
        })
        .catch(error => console.error('Lỗi:', error));
}

function updateCircleProgress(percent) {
    const circleBar = document.querySelector('.progress-bar');
    const percentText = document.querySelector('.percent-number');

    if (!circleBar || !percentText) return;

    // Do cho bán kính là 30px và công thức tính chu vi hình tròn là r * Pi * 2
    const circumference = 188.4;

    //Tính độ dời của vòng tròn xám
    //Nếu như độ dời bằng 0 thì màu xám không di chuyển mà full xanh
    //Ngược lại nếu độ dời là 188.4 thì màu xám di chuyển hết và không có màu xanh
    const offset = circumference - (circumference * percent / 100);

    circleBar.style.strokeDashoffset = offset;
    percentText.innerText = percent + "%";
}

function switchBtnGetCertificate(certId, courseId) {
    const btn = document.getElementById("btn-cert");

    if (certId > 0) {
        btn.classList.remove("disabled");
        btn.href = `personal/my-course/certificate?courseId=${courseId}`;
    } else {
        btn.classList.add("disabled");
        btn.href("javascript:void(0)");
    }

}