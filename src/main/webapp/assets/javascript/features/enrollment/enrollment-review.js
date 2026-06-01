document.addEventListener('DOMContentLoaded', function () {
    // Lấy các element cần thiết
    const stars = document.querySelectorAll('.star-rating-interactive .star-item');
    const ratingInput = document.getElementById('ratingInput');
    const errorRating = document.getElementById('error_rating');
    const starContainer = document.querySelector('.star-rating-interactive');

    // Nếu không có phần đánh giá sao trên trang thì bỏ qua
    if (!starContainer) return;

    let currentSelectedRating = 0; // Biến lưu trữ số sao đang được chọn

    // Hàm cập nhật giao diện sao (sáng/tối)
    function highlightStars(rating) {
        stars.forEach((star, index) => {
            if (index < rating) {
                // Đổi thành sao đặc (sáng)
                star.classList.remove('fa-regular');
                star.classList.add('fa-solid');
            } else {
                // Đổi thành sao rỗng (tối)
                star.classList.remove('fa-solid');
                star.classList.add('fa-regular');
            }
        });
    }

    // Xử lý sự kiện Hover (Rê chuột)
    stars.forEach(star => {
        star.addEventListener('mouseenter', function () {
            const hoverValue = parseInt(this.getAttribute('data-value'));
            highlightStars(hoverValue);
        });
    });

    // Xử lý sự kiện Mouseleave (Chuột rời khỏi khu vực sao
    starContainer.addEventListener('mouseleave', function () {
        // Trả lại hiển thị theo số sao đã click chọn trước đó
        highlightStars(currentSelectedRating);
    });

    //Xử lý sự kiện Chọn sao
    stars.forEach(star => {
        star.addEventListener('click', function () {
            // Cập nhật biến trạng thái
            currentSelectedRating = parseInt(this.getAttribute('data-value'));

            // Gán giá trị vào thẻ <input type="hidden"> để gửi form
            ratingInput.value = currentSelectedRating;

            // Khóa giao diện sao hiển thị
            highlightStars(currentSelectedRating);

            // Tự động xóa dòng báo lỗi (nếu trước đó user quên chọn sao)
            if (errorRating) {
                errorRating.textContent = '';
            }
        });
    });
});