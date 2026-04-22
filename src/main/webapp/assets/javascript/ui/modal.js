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

function openConfirmModal(
    id,
    actionUrl,
    message = "Bạn có chắc chắn muốn xóa mục này không?",
    modalId = 'confirm-delete-modal'
) {
    isBulkAction = false;
    currentDeleteId = id;
    currentDeleteUrl = actionUrl;

    // input hidden
    const inputId = document.getElementById('input-delete-id');
    if (inputId) inputId.value = id;

    const msgElem = document.getElementById('confirm-modal-message');
    const title = document.getElementById('confirm-modal-title');
    const confirmButton = document.getElementById('btn-confirm-delete');

    const config = {
        text: 'xóa',
        textHeader: 'Xóa',
        btnClass: 'btn-danger',
        titleClass: 'title-danger',
        icon: 'fa-trash'
    };

    // reset class cũ + set màu mới
    if (confirmButton) {
        confirmButton.classList.remove('btn-primary', 'btn-dark', 'btn-danger');
        confirmButton.classList.add(config.btnClass);
        confirmButton.innerText = `${config.textHeader} ngay`;
    }

    if (title) {
        title.classList.remove('title-danger', 'title-primary', 'title-dark');
        title.classList.add(config.titleClass);
        title.innerHTML = `<i class="fa-solid ${config.icon}"></i> Xác nhận ${config.text}`;
    }

    if (msgElem) {
        msgElem.innerText = message;
    }

    openModal(modalId);
}

document.addEventListener('DOMContentLoaded', function () {
    const confirmDeleteBtn = document.getElementById('btn-confirm-delete');
    if (confirmDeleteBtn) {
        confirmDeleteBtn.addEventListener('click', function () {
            if (isBulkAction) {
                const bulkForm = document.getElementById('bulkActionForm');
                const bulkInput = document.getElementById('bulkActionInput');

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
    const message = document.getElementById('confirm-modal-message')
    const title = document.getElementById('confirm-modal-title')
    const confirmButton = document.getElementById('btn-confirm-delete')

    isBulkAction = true

    if (message) {
        const actionConfig = {
            delete: {
                text: 'xóa',
                textHeader: 'Xóa',
                btnClass: 'btn-danger',
                titleClass: 'title-danger',
                icon: 'fa-trash'
            },
            duplicate: {
                text: 'nhân bản',
                textHeader: 'Nhân bản',
                btnClass: 'btn-primary',
                titleClass: 'title-primary',
                icon: 'fa-copy'
            },
            update: {
                text: 'cập nhật',
                textHeader: 'Cập nhật',
                btnClass: 'btn-dark',
                titleClass: 'title-dark',
                icon: 'fa-pen'
            }
        };

        const config = actionConfig[action] || actionConfig.update;
        confirmButton.classList.remove('btn-primary','btn-dark','btn-danger')
        title.classList.remove('title-danger', 'title-primary', 'title-dark');

        confirmButton.classList.add(config.btnClass)
        title.classList.add(config.titleClass)

        message.innerText = `Bạn có chắc muốn ${config.text} ${count} mục này không?`;
        title.innerHTML = `<i class="fa-solid ${config.icon}"></i> Xác nhận ${config.text}`;
        confirmButton.innerText = `${config.textHeader} ngay`;
    }

    window.currentBulkAction = action

    openModal('confirm-delete-modal')

}