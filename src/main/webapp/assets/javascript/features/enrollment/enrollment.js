const PlayerState = {
    currentLessonId: null,
    currentVideoType: null, // 'youtube', 'cloudinary', 'none'
    lastSavedTime: -1
}

document.addEventListener('DOMContentLoaded', function () {
    // Lấy ra tất cả bài học hay thẻ div có class là lesson-item
    const lessonItems = document.querySelectorAll('.lesson-item');
    // Lấy ra thẻ iframe để nhúng video youtube
    const youtubePlayer = document.getElementById('mainVideoPlayer');
    // Lấy ra thẻ video để chứa video từ cloudinary
    const cloudinaryPlayer = document.getElementById("cloudinaryPlayer")
    // Lấy ra thẻ div hiển thị video trôống
    const placeholder = document.getElementById('videoPlaceholder');

    // Kiểm tra nếu video đang hiển thị là cloudinary mà pause hoặc ended thì cập nhật thời gian xem mới
    // if (cloudinaryPlayer) {
    //     cloudinaryPlayer.addEventListener("pause", saveVideoLastWatched)
    //     cloudinaryPlayer.addEventListener("ended", function () {
    //         saveVideoLastWatched();
    //         autoCompleteCurrentLesson();
    //     });
    // }

    // Tạo vòng lặp quét toàn bộ các bài học đã lấy ở trên
    lessonItems.forEach(item => {
        // Lắng nghe xem nếu nó đựợc bấm (click)
        item.addEventListener('click', function () {

            // Lưu lại thời gian coi cuối của video hiện tại trước khi chuyển sang video khasc
            if (PlayerState.currentVideoType === 'cloudinary') {
                saveVideoLastWatched()
            }

            // Lấy ra đường dẫn URL từ trong thẻ div bài học đó
            const rawUrl = this.getAttribute('data-video-url');
            // Lấy ra thời gian xem cuối được truyền bên JSP
            const lastTime = parseInt(this.getAttribute('data-last-time')) || 0;
            // Gán lesson id cho PlayerState để lúc cập nhật video có thể lấy xài
            PlayerState.currentLessonId = this.getAttribute('data-lesson-id')
            // Reset lại cờ hiệu khi đổi video mới
            PlayerState.lastSavedTime = -1;
            loadLessonNotes(PlayerState.currentLessonId);

            let finalUrl = VideoHelper.formatVideoUrl(rawUrl);

            if (!finalUrl) {
                // Hiển thị Placeholder (Thông báo không có video)
                if (youtubePlayer) {
                    youtubePlayer.style.display = 'none';
                    youtubePlayer.src = ""; // Xóa src cũ để tránh tiếng video vẫn phát ngầm
                }
                // Hiển thị Video
                if (cloudinaryPlayer) {
                    cloudinaryPlayer.src = "";
                    cloudinaryPlayer.style.display = 'none';
                }
                if (placeholder) {
                    placeholder.style.display = 'flex';
                }

            } else if (VideoHelper.isEmbedSource(rawUrl)) {
                PlayerState.currentVideoType = 'youtube'
                // Hiển thị Video
                if (youtubePlayer) {
                    youtubePlayer.src = finalUrl;
                    youtubePlayer.style.display = 'block';
                }
                if (placeholder) {
                    placeholder.style.display = 'none';
                }
                if (cloudinaryPlayer) {
                    cloudinaryPlayer.src = "";
                    cloudinaryPlayer.style.display = 'none';
                }
            } else {
                PlayerState.currentVideoType = 'cloudinary'
                // Ẩn đi video youtuber
                if (youtubePlayer) {
                    youtubePlayer.src = "";
                    youtubePlayer.style.display = 'none';
                }
                // Hiển thị Video Cloudinary
                if (cloudinaryPlayer) {
                    cloudinaryPlayer.src = finalUrl;
                    cloudinaryPlayer.style.display = 'block';
                    // Đưa thời gian xem hiện tại của video thành thời gian xem cuối đã được truyền bên JSP
                    cloudinaryPlayer.onloadedmetadata = function () {
                        if (lastTime > 0) cloudinaryPlayer.currentTime = lastTime;
                        cloudinaryPlayer.pause()
                    };

                    cloudinaryPlayer.onpause = function () {
                        saveVideoLastWatched()
                    }

                    cloudinaryPlayer.onended =function () {
                        saveVideoLastWatched()
                        autoCompleteCurrentLesson()
                        playNextLesson();
                    }
                }
                // Ẩn ảnh video mặc định
                if (placeholder) {
                    placeholder.style.display = 'none';
                }
            }

            // Loại bỏ active-lesson cho toàn bộ bài học
            lessonItems.forEach(li => li.classList.remove('active-lesson'));
            // Gán active-lesson cho bài học hiện tại
            this.classList.add('active-lesson');
        });
    });

    // Luôn lấy ra video đầu tiên
    if (lessonItems.length > 0) {
        lessonItems[0].click();
    }
});

// ======================== LƯU THỜI GIAN XEM CUỐI CUÙNG =============================
function saveVideoLastWatched() {
    // Lấy ra thẻ chứa video từ cloudinary
    const cloudinaryPlayer = document.getElementById("cloudinaryPlayer");
    // Lấy ra thời gian hiện tại và làm tròn từ video của cloudinary
    const currentTime = Math.floor(cloudinaryPlayer.currentTime);
    // Lấy ra id của lesson hiện tại đang phát (được lưu ở trên)
    const currentLessonId = PlayerState.currentLessonId

    // KIỂM TRA TRÙNG LẶP: Nếu thời gian này vừa mới gửi xong thì KHÔNG gửi nữa
    if (PlayerState.lastSavedTime === currentTime) {
        return;
    }

    // Đảm bảo loại là cloudinary, có thẻ video và currentLessonId
    if (PlayerState.currentVideoType === 'cloudinary' && cloudinaryPlayer && currentLessonId) {
        // Cập nhật cờ hiệu lưu thời gian cuối cùng
        PlayerState.lastSavedTime = currentTime;
        // Bắt đầu fetch dữ liệu
        fetch('personal/my-course/update-time', {
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded', 'X-CSRF-Token': getCsrfToken()},
            body: `lessonId=${currentLessonId}&lastWatchedTime=${currentTime}`
        })
            .then(response => {
                return response.json();
            })
            .then(data => {
                // Nếu trả về là thành công
                if (data.status === 'success') {
                    const activeItem = document.querySelector(`.lesson-item[data-lesson-id="${currentLessonId}"]`);
                    // Cập nhật thời gian cuối cùng vô attribute là data-last-time
                    if (activeItem) {
                        activeItem.setAttribute('data-last-time', currentTime);
                    }
                }
            })
            .catch(err => console.error("--- LỖI FETCH (Sai URL hoặc mạng):", err));
    }
}

// ======================= XỬ LÝ CLICK CHỌN VIDEO & TÍCH XONG BÀI HỌC ===================================
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

// ============================= CẬP NHẬT TIẾN ĐỘ HOÀN THÀNH KHÓA HỌC ========================================
function updateProgress(lessonId, isCompleted, enrollmentId, courseId) {
    fetch('personal/my-course/detail', {
        // Gỉa lập 1 cái form để gửi nó xuống
        method: 'POST',
        headers: {
            //Dùng để nói với Server cái dữ liệu gửi trong Body có định dạng giống hệt như một cái Form HTML truyền thống
            'Content-Type': 'application/x-www-form-urlencoded',
            'X-CSRF-Token': getCsrfToken()
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

// ========================= HIỆN NÚT LÂẤY CHỨNG CHỈ KHI HOÀN THÀNH KHÓA HỌC ========================
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

// ============ TỰ ĐỘNG TICK CHECK BOX SAU KHI THỜI GIAN XEM HẾT VIDEO ==============
function autoCompleteCurrentLesson() {
    const currentLessonId = PlayerState.currentLessonId; // Đây là Lesson ID (ví dụ: 423)

    if (!currentLessonId) return;

    // Bước 1: Tìm thẻ div cha (lesson-item) đang bọc bài học hiện tại theo ID của Table Lesson
    const activeLessonItem = document.querySelector(`.lesson-item[data-lesson-id="${currentLessonId}"]`);

    if (!activeLessonItem) {
        return;
    }

    // Bước 2: Tìm cái checkbox chui rúc BÊN TRONG thẻ div đó (Không cần biết data-lesson-id của nó là gì)
    const checkbox = activeLessonItem.querySelector('.lesson-checkbox');
    const enrollmentIdElement = document.getElementById('enrollment-id');
    const courseIdElement = document.getElementById("course-id");

    if (!checkbox || !enrollmentIdElement || !courseIdElement) {
        return;
    }

    if (checkbox.checked) return;

    // Bước 3: Ép giao diện hiển thị tick xanh
    checkbox.checked = true;

    // Bước 4: Lấy ra ID của UserLessonProgress từ checkbox ở trên đã tìm theo ID của Lesson
    const progressIdForServer = checkbox.getAttribute('data-lesson-id'); // Đây chính là l.id
    const enrollmentId = enrollmentIdElement.value;
    const courseId = courseIdElement.value;

    updateProgress(progressIdForServer, true, enrollmentId, courseId);
}

// ==================== TỰ ĐỘNG CHUYỂN BÀI HỌC KẾ TIẾP ====================
function playNextLesson() {
    // Lấy lại danh sách tất cả các bài học trên menu thành 1 mảng (Array)
    const allLessons = Array.from(document.querySelectorAll('.lesson-item'));

    // Tìm bài học đang được bôi đậm (active)
    const currentActiveItem = document.querySelector('.lesson-item.active-lesson');

    //Nếu không có thì thoát hàm
    if (!currentActiveItem) {
        return;
    }

    // Tìm vị trí (index) của bài hiện tại trong mảng
    const currentIndex = allLessons.indexOf(currentActiveItem);

    // Nếu mảng hợp lệ và bài hiện tại chưa phải là bài cuối cùng
    if (currentIndex !== -1 && currentIndex < allLessons.length - 1) {
        const nextLesson = allLessons[currentIndex + 1];

        // Cuộn menu sao cho bài tiếp theo hiển thị trong tầm mắt
        nextLesson.scrollIntoView({ behavior: "smooth", block: "center" });

        // Giả lập hành vi click chuột của người dùng
        nextLesson.click();
    } else {
        console.log("Đây là video cuối cùng của khóa học. Dừng phát tự động.");
    }
}