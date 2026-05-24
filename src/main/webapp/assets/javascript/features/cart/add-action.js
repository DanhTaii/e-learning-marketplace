function addToWishlist(e, btn, courseId) {
    if (e) {
        e.preventDefault();
        e.stopPropagation();
    }
    // 1. Gửi request lên server
    fetch('personal/my-wishlist?courseId=' + courseId, {
        method: 'POST',
        headers: {
            'X-Requested-With': 'XMLHttpRequest',
            'X-CSRF-Token': getCsrfToken()
        },
    })
        .then(response => {
            if (response.status === 401) {
                window.location.href = "sign-in?error=auth_required";
                return null;
            }
            return response.text();
        })
        .then(status => {
            if (!status) return;

            const icon = btn.querySelector('i');

            if (status.trim() === 'added') {
                btn.classList.add('active');
                icon.classList.add('animate-heart'); // Thêm hiệu ứng nảy
                // Xóa class animation sau khi chạy xong để lần sau bấm lại vẫn nảy
                setTimeout(() => icon.classList.remove('animate-heart'), 400);
            } else {
                btn.classList.remove('active');
                icon.style.color = '#ccc'; // Trả về màu xám
            }
        })
        .catch(err => console.error("Lỗi Wishlist:", err));
}

function addToCart(e, courseId) {
    e.preventDefault();
    e.stopPropagation();
    fetch('add-cart?id=' + courseId, {
        method: 'GET',
        headers: {
            'X-Requested-With': 'XMLHttpRequest',
            'X-CSRF-Token': getCsrfToken()
        }
    })
        .then(response => {
            if (response.status === 401) {
                window.location.href = "sign-in?error=auth_required";
                return null;
            }
            if (response.ok) return response.text();
            throw new Error('Network response was not ok.');
        })
        .then(newCount => {
            if (newCount === null) return;
            const cartElement = document.getElementById('cart-count');
            if (cartElement) {
                cartElement.innerText = newCount;
            }
            if (window.location.href.includes("cart")) {
                window.location.reload();
            } else {
                toast({
                    title: 'Thành công!',
                    message: 'Đã thêm khóa học vào giỏ hàng!',
                    type: 'success',
                    duration: 2000
                });
            }
        })
        .catch(error => {
            console.error('Lỗi AJAX:', error);
            toast({
                title: 'Thất bại!',
                message: 'Không thể thêm vào giỏ hàng, vui lòng thử lại.',
                type: 'error',
                duration: 2000
            });
        });
}
function goToCourseContent(e, url) {
    if (e) {
        e.preventDefault();
        e.stopPropagation();
    }
    window.location.href = url;
}

// ép load tại trang khi bấm back
window.addEventListener("pageshow", function (event) {
    var historyTraversal = event.persisted
    if (historyTraversal) {
        window.location.reload();
    }
});