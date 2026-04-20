let currentDeleteId = null;
let currentDeleteUrl = "";
let isBulkAction = false;

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
        closeModal(event.target.id)
        if (event.target.id === 'confirm-delete-modal') {
            currentDeleteId = null;
            isBulkAction = false;
        }
    }
});

function openConfirmModal(id, actionUrl, message = "Bạn có chắc chắn muốn xóa mục này không?", modalId = 'confirm-delete-modal') {
    isBulkAction = false
    currentDeleteId = id;
    currentDeleteUrl = actionUrl;
    // Nạp ID vào input ẩn
    const inputId = document.getElementById('input-delete-id');
    if (inputId) inputId.value = id;

    const msgElem = document.getElementById('confirm-modal-message');
    if (msgElem) msgElem.innerText = message;

    openModal(modalId);
}

document.addEventListener('DOMContentLoaded', function () {
    const confirmDeleteBtn = document.getElementById('btn-confirm-delete');
    if (confirmDeleteBtn) {
        confirmDeleteBtn.addEventListener('click', function () {
            if (isBulkAction) {
                const bulkForm = document.getElementById('bulkActionForm');
                const bulkInput = document.getElementById('bulkActionInput')

                if (bulkInput && bulkForm) {
                    bulkInput.value = window.currentBulkAction;
                    bulkForm.submit();
                }
            } else if (currentDeleteId && currentDeleteUrl) {
                const form = document.getElementById('delete-form-id');
                form.action = currentDeleteUrl;
                form.submit();
            }
        });
    }
});

function openConfirmBulkAction(action, count) {
    const messgae = document.getElementById('confirm-modal-message')

    isBulkAction = true

    if (messgae) {
        let actionText = action === 'delete' ? 'xóa' : (action === 'duplicate' ? 'nhân bản' : 'cập nhật');
        messgae.innerText = `Bạn có chắc muốn ${actionText} ${count} mục này không ?`
    }

    window.currentBulkAction = action

    openModal('confirm-delete-modal')

}