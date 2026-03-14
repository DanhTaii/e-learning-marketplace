$(document).ready(function () {
    Validator.setupAutoClearErrors();

    $('#courseForm').on('submit', function (e) {
        let isValid = true;

        // 1. Lấy giá trị
        const title = $('#courseTitle').val() || "";
        const subtitle = $('#courseSubtitle').val() || "";
        const price = $('#coursePrice').val() || "";
        const discount = $('#courseDiscountPrice').val() || "";
        const category = $('#courseCategory').val() || "";
        const goals = $('#courseGoals').val() || "";
        const description = $('#courseDescription').val() || "";

        // Kiểm tra Tên khóa học
        const titleLengthErr = Validator.checkLength(title, 10, 150, "Tên khóa học");
        if (titleLengthErr) {
            $('#error_courseTitle').text(titleLengthErr);
            isValid = false;
        }

        // Kiểm tra Phụ đề
        const subtitleErr = Validator.checkLength(subtitle, 10, 250, "Phụ đề");
        if (subtitleErr) {
            $('#error_courseSubtitle').text(subtitleErr);
            isValid = false;
        }

        // Kiểm tra Mục tiêu
        const goalsErr = Validator.checkLength(goals, 20, 1000, "Mục tiêu");
        if (goalsErr) {
            $('#error_courseGoals').text(goalsErr);
            isValid = false;
        }


        // Kiểm tra Mô tả
        const descErr = Validator.checkLength(description, 50, 5000, "Mô tả");
        if (descErr) {
            $('#error_courseDescription').text(descErr);
            isValid = false;
        }


        // Kiểm tra Giá bán
        const priceErr = Validator.checkPrice(price);
        if (priceErr) {
            $('#error_coursePrice').text(priceErr);
            isValid = false;
        }

        // Kiểm tra Giá giảm (Nếu có nhập thì mới check logc)
        const discountErr = Validator.checkDiscount(price, discount);
        if (discountErr) {
            $('#error_courseDiscount').text(discountErr);
            isValid = false;
        }

        // Kiểm tra Danh mục
        if (!category) {
            $('#error_courseCategory').text("Vui lòng chọn một danh mục");
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
    });
});