$(document).ready(function () {
    // Tự động xóa lỗi khi người dùng bắt đầu nhập lại vào ô input
    BaseValidator.setupAutoClearErrors();

    $('#voucherForm').on('submit', function (e) {
        let isValid = true;

        // 1. Lấy các giá trị từ form
        const code = $('input[name="code"]').val().trim();
        const title = $('input[name="title"]').val().trim();
        // const description = $('textarea[name="description"]').val().trim(); // Tùy chọn, nếu bắt buộc thì validate

        const discountType = $('#discountTypeSelect').val();
        const discountValueStr = $('input[name="discountValue"]').val().trim();
        const minOrderValueStr = $('input[name="minOrderValue"]').val().trim();
        const maxDiscountValueStr = $('input[name="maxDiscountValue"]').val().trim();

        const startDate = $('input[name="startDate"]').val();
        const endDate = $('input[name="endDate"]').val();

        const usageLimitStr = $('input[name="usageLimit"]').val().trim();

        // 2. Validate Mã Voucher (Code)
        if (!code) {
            $('#error_code').text("Vui lòng nhập mã Voucher");
            isValid = false;
        } else if (code.length < 3 || code.length > 50) {
            $('#error_code').text("Mã Voucher phải từ 3 đến 50 ký tự");
            isValid = false;
        }

        // 3. Validate Tiêu đề (Sử dụng hàm của BaseValidator)
        const titleErr = BaseValidator.checkLength(title, 5, 255, "Tên chương trình");
        if (titleErr) {
            $('#error_title').text(titleErr);
            isValid = false;
        }

        // 4. Validate Mức giảm giá
        if (!discountValueStr) {
            $('#error_discountValue').text("Vui lòng nhập mức giảm giá");
            isValid = false;
        } else {
            const discountValue = parseFloat(discountValueStr);
            if (isNaN(discountValue) || discountValue <= 0) {
                $('#error_discountValue').text("Mức giảm phải lớn hơn 0");
                isValid = false;
            } else if (discountType === 'PERCENT' && discountValue > 100) {
                $('#error_discountValue').text("Mức giảm theo phần trăm không được vượt quá 100%");
                isValid = false;
            }
        }

        // 5. Validate Đơn hàng tối thiểu (Có thể nhập 0, nhưng không được âm)
        if (minOrderValueStr) {
            const minOrderValue = parseFloat(minOrderValueStr);
            if (isNaN(minOrderValue) || minOrderValue < 0) {
                $('#error_minOrderValue').text("Đơn tối thiểu không được là số âm");
                isValid = false;
            }
        }

        // 6. Validate Mức giảm tối đa (Chỉ áp dụng khi chọn % và có nhập dữ liệu)
        if (discountType === 'PERCENT' && maxDiscountValueStr) {
            const maxDiscountValue = parseFloat(maxDiscountValueStr);
            if (isNaN(maxDiscountValue) || maxDiscountValue < 0) {
                $('#error_maxDiscountValue').text("Mức giảm tối đa không được là số âm");
                isValid = false;
            }
        }

        // 7. Validate Thời gian (Ngày bắt đầu & Ngày kết thúc)
        if (!startDate) {
            $('#error_startDate').text("Vui lòng chọn ngày bắt đầu");
            isValid = false;
        }
        if (!endDate) {
            $('#error_endDate').text("Vui lòng chọn ngày kết thúc");
            isValid = false;
        }

        // Kiểm tra logic: Ngày kết thúc phải sau ngày bắt đầu
        if (startDate && endDate) {
            const startTimestamp = new Date(startDate).getTime();
            const endTimestamp = new Date(endDate).getTime();

            if (endTimestamp <= startTimestamp) {
                $('#error_endDate').text("Thời gian kết thúc phải sau thời gian bắt đầu");
                isValid = false;
            }
        }

        // 8. Validate Lượt sử dụng (Có thể để trống = không giới hạn, nếu nhập phải > 0)
        if (usageLimitStr) {
            const usageLimit = parseInt(usageLimitStr);
            if (isNaN(usageLimit) || usageLimit <= 0) {
                $('#error_usageLimit').text("Tổng lượt sử dụng phải là số nguyên dương");
                isValid = false;
            }
        }

        // 9. Xử lý cuối cùng
        if (!isValid) {
            console.log("Phát hiện lỗi ở form Voucher, đang chặn gửi...");
            e.preventDefault(); // Chặn form submit
            $('html, body').animate({
                scrollTop: $('.error-client:not(:empty)').first().closest('.form-group').offset().top - 50
            }, 'slow');
        } else {
            console.log("Dữ liệu hợp lệ, chuẩn bị gửi lên Server!");
        }

        return isValid;
    });
});