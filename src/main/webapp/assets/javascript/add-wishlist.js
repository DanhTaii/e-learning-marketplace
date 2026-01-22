function addToWishlist(btn, courseId) {
    // 1. Gửi request lên server
    fetch('personal/my-wishlist', {
        method: 'POST',
        headers: {
            // Quan trọng: Phải có header này để Servlet đọc được Parameter
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: 'courseId=' + courseId
    })
        .then(response => {
            if (response.status === 401) {
                alert("Bạn cần đăng nhập để thực hiện tính năng này!");
                window.location.href = "sign-in";
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