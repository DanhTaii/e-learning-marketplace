document.addEventListener('DOMContentLoaded', function () {
    initStarRating();
    initReviewForm();
});

// ==========================================
// XỬ LÝ GIAO DIỆN CHỌN SAO (RATING)
// ==========================================
function initStarRating() {
    const starContainer = document.querySelector('.star-rating-interactive');
    if (!starContainer) return; // Nếu không có HTML này thì bỏ qua (tránh lỗi trang khác)

    const stars = starContainer.querySelectorAll('.star-item');
    const ratingInput = document.getElementById('ratingInput');
    const errorRating = document.getElementById('error_rating');
    let currentSelectedRating = 0;

    // Hàm nội bộ: highlight sao
    const highlightStars = (rating) => {
        stars.forEach((star, index) => {
            if (index < rating) {
                star.classList.remove('fa-regular');
                star.classList.add('fa-solid');
            } else {
                star.classList.remove('fa-solid');
                star.classList.add('fa-regular');
            }
        });
    };

    // Sự kiện: Hover vào sao
    stars.forEach(star => {
        star.addEventListener('mouseenter', function () {
            const hoverValue = parseInt(this.getAttribute('data-value'));
            highlightStars(hoverValue);
        });

        // Sự kiện: Click chọn sao
        star.addEventListener('click', function () {
            currentSelectedRating = parseInt(this.getAttribute('data-value'));
            ratingInput.value = currentSelectedRating;
            highlightStars(currentSelectedRating);

            if (errorRating) errorRating.textContent = '';
        });
    });

    // Sự kiện: Rời chuột khỏi khu vực sao
    starContainer.addEventListener('mouseleave', function () {
        highlightStars(currentSelectedRating);
    });
}

// ==========================================
// XỬ LÝ SUBMIT FORM BẰNG AJAX
// ==========================================
function initReviewForm() {
    const reviewForm = document.getElementById('myForm');
    if (!reviewForm) return;

    reviewForm.addEventListener('submit', handleReviewSubmit);
}

// Tách riêng hàm xử lý logic submit để code không bị lồng quá sâu
async function handleReviewSubmit(e) {
    e.preventDefault(); // Chặn load trang

    const form = e.target;
    const courseId = form.querySelector('input[name="courseId"]').value;
    const ratingInput = form.querySelector('input[name="rating"]');
    const commentInput = form.querySelector('textarea[name="comment"]');
    const csrfToken = form.querySelector('input[name="csrfToken"]').value;
    const btnSubmit = form.querySelector('.btn-submit-modern');

    const rating = ratingInput.value;
    const comment = commentInput.value.trim();

    // Validate nhẹ trước khi gọi Server
    let isValid = true;
    if (rating == 0 || rating === "") {
        document.getElementById('error_rating').textContent = 'Vui lòng chọn số sao đánh giá.';
        isValid = false;
    }
    if (comment === '') {
        document.getElementById('error_comment').textContent = 'Vui lòng nhập nội dung đánh giá.';
        isValid = false;
    }
    if (!isValid) return;

    // Hiển thị trạng thái đang gửi
    const originalBtnText = btnSubmit.innerHTML;
    btnSubmit.innerHTML = '<i class="fa-solid fa-spinner fa-spin"></i> Đang gửi...';
    btnSubmit.disabled = true;

    try {
        const response = await fetch('my-course/review/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'X-CSRF-Token': csrfToken
            },
            body: `courseId=${courseId}&rating=${rating}&comment=${encodeURIComponent(comment)}`
        });

        const data = await response.json();

        if (data.status === 'success') {
            toast({ title: 'Thành công!', message: 'Đánh giá của bạn đã được ghi nhận.', type: 'success', duration: 3000 });

            // Thêm dòng này để ẨN FORM ĐÁNH GIÁ đi ngay lập tức
            const formContainer = document.querySelector('.modern-review-form');
            // const alreadyReviewedMessage = document.querySelector('.already-reviewed-message');
            if (formContainer) {
                formContainer.style.display = 'none'; // Giấu form đi
                // alreadyReviewedMessage.style.display = 'block'; // Hiện thông báo đã đánh giá
            }

            resetReviewFormState(commentInput, ratingInput);
            appendNewReviewToDOM(rating, comment);

            // Ẩn bảng trống nếu có
            const emptyState = document.getElementById('empty-review-state');
            if (emptyState) emptyState.style.display = 'none';
        } else {
            toast({ title: 'Lỗi!', message: 'Có lỗi xảy ra khi lưu đánh giá.', type: 'error', duration: 3000 });
        }
    } catch (error) {
        console.error('Lỗi khi submit đánh giá:', error);
        toast({ title: 'Lỗi mạng!', message: 'Vui lòng thử lại sau.', type: 'error', duration: 3000 });
    } finally {
        // Trả lại trạng thái cho nút
        btnSubmit.innerHTML = originalBtnText;
        btnSubmit.disabled = false;
    }
}

// Hàm hỗ trợ: Làm sạch form sau khi gửi thành công
function resetReviewFormState(commentInput, ratingInput) {
    commentInput.value = '';
    ratingInput.value = 0;
    document.getElementById('error_comment').textContent = '';
    document.getElementById('error_rating').textContent = '';

    document.querySelectorAll('.star-rating-interactive .star-item').forEach(star => {
        star.classList.remove('fa-solid');
        star.classList.add('fa-regular');
    });
}

// THÊM KHỐI BLOCK REVIEW KHI VỪA MỚI GỬI FORM TẠO REVIEW
function appendNewReviewToDOM(rating, comment) {
    const avatarImg = document.querySelector('.user__avatar2');
    const avatarUrl = avatarImg ? avatarImg.src : 'assets/image/default-avatar.png';
    const userName = "Bạn";

    // Lấy ngày hiện tại: YYYY-MM-DD
    const today = new Date();
    const dateStr = today.getFullYear() + '-' +
        String(today.getMonth() + 1).padStart(2, '0') + '-' +
        String(today.getDate()).padStart(2, '0');

    const reviewHtml = `
        <div class="review-box__comment" style="animation: fadeIn 0.5s;">
            <div class="comment__user header__user">
                <img src="${avatarUrl}" alt="Avatar" class="user__avatar1">
            </div>
            <div class="comment__box">
                <div class="box__name box">
                    <div class="review-in4">
                        <span class="review__name">${userName}</span>
                        <span class="review__time">${dateStr}</span>
                    </div>
                </div>
                <div class="box__date box">
                    <div class="star">
                        <div class="text-medium regular">${rating}</div>
                        <div class="star-icon">
                            <i class="fa-solid fa-star"></i>
                        </div>
                    </div>
                </div>
                <div class="box__comment box">
                    <span>${comment}</span>
                </div>
            </div>
        </div>
    `;

    const container = document.getElementById('reviews-list-container');
    if (container) {
        container.insertAdjacentHTML('afterbegin', reviewHtml);
    }
}