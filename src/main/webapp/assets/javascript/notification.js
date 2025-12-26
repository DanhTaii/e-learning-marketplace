function toast({title = '', message = '', type = 'info', duration = 3000}) {
    console.log("Toast Type:", type);
    const main = document.getElementById('toast');
    if (main) {
        // Bổ sung logic chọn icon
        const icons = {
            success: 'fa-solid fa-circle-check',
            info: 'fa-solid fa-circle-info',
            warning: 'fa-solid fa-circle-exclamation',
            error: 'fa-solid fa-circle-xmark'
        };

        const icon = icons[type]; // Lấy icon tương ứng

        const toast = document.createElement('div');

        const autoRemoveId = setTimeout(function () {
            main.removeChild(toast);
        }, duration + 1000);

        toast.onclick = function (e) {
            if (e.target.closest('.toast__close')) {
                main.removeChild(toast);
                clearTimeout(autoRemoveId);
            }
        };
        const delay = (duration / 1000).toFixed(2);

        toast.classList.add('toast');
        toast.classList.add('toast--' + type);
        toast.style.animation = `slideInLeft ease 0.3s, fadeOut linear 1s ${delay}s forwards`;

        toast.innerHTML = `
                <div class="toast__icon"><i class="${icon}"></i></div>
                <div class="toast__body">
                    <h3 class="toast__title">` + title + `</h3>
                    <p class="toast__msg">` + message + `</p>
                </div>
                <div class="toast__close"><i class="fa-solid fa-xmark"></i></div>
            `;
        main.appendChild(toast);
    }
}

function showNotificationModal(title, message, isSuccess) {
    const titleElem = document.getElementById('noti-title');
    const msgElem = document.getElementById('noti-message');

    titleElem.innerText = title;
    // Đổi màu tiêu đề tùy theo là lỗi hay thành công
    titleElem.style.color = isSuccess ? '#28a745' : '#dc3545';
    msgElem.innerText = message;

    document.getElementById('notification-modal').style.display = 'flex';
}

// Hàm đóng modal thông báo
function closeNotiModal() {
    document.getElementById('notification-modal').style.display = 'none';
}

window.addEventListener('load', function () {
    // 1. Lấy giá trị an toàn từ window (đã được JSP đổ vào)
    const errorMsg = (window.flashError || "").trim();
    const successMsg = (window.flashSuccess || "").trim();

    // 2. Ưu tiên hiển thị Toast cho các hành động thành công
    if (successMsg !== "") {
        toast({
            title: 'Thành công!',
            message: successMsg,
            type: 'success',
            duration: 4000
        });
    }

    // 3. Nếu là lỗi, bạn có thể chọn hiện Toast hoặc Modal tùy ý
    if (errorMsg !== "") {
        // Ví dụ: Lỗi thường thì hiện Toast
        toast({
            title: 'Thất bại!',
            message: errorMsg,
            type: 'error',
            duration: 6000
        });

        // Hoặc nếu lỗi quá nặng thì hiện Modal (bỏ comment nếu muốn dùng)
        // showNotificationModal("LỖI HỆ THỐNG", errorMsg, false);
    }
});

// Cập nhật hàm onclick dùng chung để không bị ghi đè
window.onclick = function (event) {
    const notiModal = document.getElementById('notification-modal');
    const userDetailModal = document.getElementById('user-detail');

    if (notiModal && event.target == notiModal) {
        closeNotiModal();
    }
    if (userDetailModal && event.target == userDetailModal) {
        closeModal();
    }
}