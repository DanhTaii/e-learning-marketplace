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
// ==================== CLOSE - OPEN - ACTION ====================
// Hàm mở bất kỳ modal nào bằng ID => Phụ trợ cho currentDeleteId
function openModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) modal.style.display = 'flex';
}

// Hàm đóng bất kỳ modal nào bằng ID => Phụ trợ cho currentDeleteId
function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) modal.style.display = 'none';
}

// Lắng nghe sự kiện click toàn trang
window.onclick = function (event) {
    // 1. Tự động đóng nếu click vào vùng nền mờ (vùng có class 'modal')
    if (event.target.classList.contains('modal')) {
        event.target.style.display = 'none';
        // Reset ID xóa nếu đó là modal xác nhận
        if (event.target.id === 'confirm-delete-modal') currentDeleteId = null;
    }
};
// ==================== CLOSE - OPEN - ACTION ====================

// Xử lý riêng cho chức năng XÓA (Cần lưu lại ID)
let currentDeleteId = null;
function openConfirmModal(id, modalId = 'confirm-delete-modal') {
    currentDeleteId = id;
    document.getElementById('input-delete-id').value = id;
    openModal(modalId);
}

// Gắn sự kiện cho nút "Xác nhận xóa" trong modal (Dùng chung)
const confirmDeleteBtn = document.getElementById('btn-confirm-delete');
if (confirmDeleteBtn) { // Kiểm tra nếu tìm thấy nút thì mới gán sự kiện
    confirmDeleteBtn.onclick = function () {
        if (currentDeleteId) {
            const form = document.getElementById('delete-form-id');
            if (form) form.submit();
        }
    }
}

window.addEventListener('load', function () {
    // Ép kiểu về chuỗi và trim, đồng thời kiểm tra nếu là chữ "null" thì coi như rỗng
    const errorMsg = String(window.flashError || "").trim();
    const successMsg = String(window.flashSuccess || "").trim();

    console.log("Success message received:", successMsg); // Dòng này để debug

    if (successMsg !== "" && successMsg !== "null") {
        toast({title: 'Thành công!', message: successMsg, type: 'success', duration: 4000});
    }

    if (errorMsg !== "" && errorMsg !== "null") {
        toast({title: 'Thất bại!', message: errorMsg, type: 'error', duration: 6000});
    }
});