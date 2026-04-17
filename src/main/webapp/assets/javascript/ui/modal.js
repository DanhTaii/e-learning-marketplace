let currentDeleteId = null;
let currentDeleteUrl = "";

function openModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) {
        modal.classList.add('show'); // Thêm class show
    }
}

function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) {
        modal.classList.remove('show'); // Xóa class show
    }
}

window.addEventListener('click', function (event) {
    if (event.target.classList.contains('modal')) {
        event.target.style.display = 'none';
        if (event.target.id === 'confirm-delete-modal') currentDeleteId = null;
    }
});

function openConfirmModal(id, actionUrl, message = "Bạn có chắc chắn muốn xóa mục này không?", modalId = 'confirm-delete-modal') {
    currentDeleteId = id;
    currentDeleteUrl = actionUrl;
    // Nạp ID vào input ẩn
    const inputId = document.getElementById('input-delete-id');
    if(inputId) inputId.value = id;

    const msgElem = document.getElementById('confirm-modal-message');
    if(msgElem) msgElem.innerText = message;

    openModal(modalId);
}

document.addEventListener('DOMContentLoaded', function() {
    const confirmDeleteBtn = document.getElementById('btn-confirm-delete');
    if (confirmDeleteBtn) {
        confirmDeleteBtn.addEventListener('click', function () {
            if (currentDeleteId && currentDeleteUrl) {
                const form = document.getElementById('delete-form-id');
                form.action = currentDeleteUrl;
                form.submit();
            }
        });
    }
});