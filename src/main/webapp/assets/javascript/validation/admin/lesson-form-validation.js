$(document).ready(function () {
    BaseValidator.setupAutoClearErrors();

    $('#lessonForm').on('submit', function (e) {
        let isValid = true;

        // 1. Lấy giá trị
        // const courseId = $('#selectCourse').val() || "";
        // const orderIndex = $('#orderIndex').val() || "";
        const title = $('#lessonTitle').val() || "";
        const durationMinutes = $('#durationMinutes').val() || "";
        const videoUrlInput = $('#videoUrlInput').val() || "";
// Cách lấy file an toàn
        const videoFileInput = $('#videoFile')[0];
        const videoFile = (videoFileInput && videoFileInput.files) ? videoFileInput.files[0] : null;
        // // Kiểm tra Khóa học
        // if (courseId === "0" || !courseId) {
        //     $('#error_idCourse').text("Vui lòng chọn khóa học");
        //     isValid = false;
        // }
        //
        // // Kiểm tra Thứ tự
        // const orderIndexErr = BaseValidator.checkPositiveInteger(orderIndex, "Thứ tự");
        // if(orderIndexErr) {
        //     $('#error_orderIndex').text(orderIndexErr);
        //     isValid = false;
        // }

        const titleErr = BaseValidator.checkLength(title, 5, 255, "Tiêu đề bài học");
        if(titleErr) {

            $('#error_lessonTitle').text(titleErr);
            isValid = false;
        }

        const durationErr = BaseValidator.checkPositiveInteger(durationMinutes, "Thời lượng (phút)");
        if(durationErr) {
            $('#error_durationMinutes').text(durationErr);
            isValid = false;
        }

        if (!videoUrlInput && !videoFile) {
            $('#error_videoUrl').text("Vui lòng nhập URL video");
            isValid = false;
        } else if (videoUrlInput && videoFile) {
            $('#error_videoUrl').text("Chỉ được chọn URL hoặc upload file, không chọn cả hai");
            isValid = false;
        }

        if (!isValid) {
            console.log("Phát hiện có lỗi, đang chặn form...");
            e.preventDefault();
            $('html, body').animate({scrollTop: 0}, 'slow');
        } else {
            console.log("Mọi thứ ok, chuẩn bị gửi lên Server!");
        }
        return isValid;
    })
})