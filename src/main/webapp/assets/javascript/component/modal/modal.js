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

let modalState = {
    ids: [],
    action: '',
    url: '',
    isBulk: false
}

function setupConfirmModal(options) {
    const {action, ids, url, isBulk, count} = options;
    const config = MODAL_CONFIGS[action];

    modalState = {ids, action, url, isBulk};

    const message = document.getElementById('confirm-modal-message')
    const title = document.getElementById('confirm-modal-title')
    const confirmButton = document.getElementById('btn-confirm-delete')
    const reasonContainer = document.getElementById('reason-container')

    // XÓA BỎ CÁC CSS CŨ KHI CÁC HÀNH ĐỘNG KHÁC CẦN BẬT MODAL
    confirmButton.classList.remove('btn-primary', 'btn-dark', 'btn-danger')
    title.classList.remove('title-danger', 'title-primary', 'title-dark');

    // THÊM NỘI DUNG THÔNG ĐIỆP ĐÃ CẤU HÌNH SẴN
    message.innerText = `Bạn có chắc muốn ${config.title.toLowerCase()} ${count} mục này không?`;

    //THÊM CSS, NỘI DUNG TITLE ĐÃ CẤU HÌNH SẴN
    title.classList.add(config.titleClass)
    title.innerHTML = `<i class="fa-solid ${config.icon}"></i> Xác nhận ${config.title}`;

    // THÊM CSS TITLE, BTN CHO ACTION HIỆN TẠI
    confirmButton.classList.add(config.btnClass)
    confirmButton.innerText = config.btnText

    reasonContainer.style.display = config.showReason ? 'block' : 'none';

    openModal('confirm-delete-modal')

}

document.addEventListener('DOMContentLoaded', function () {
    const confirmBtn = document.getElementById('btn-confirm-delete');

    if (confirmBtn) {
        confirmBtn.addEventListener('click', function () {
            const currentReason = document.getElementById('archive-reason').value

            if (modalState.isBulk) {
                submitBulkForm(modalState.action, currentReason)
            } else {
                submitSingleForm(modalState.action, modalState.url, currentReason)
            }
        })
    }

})

function submitBulkForm(action, reason) {
    const form = document.getElementById('bulkActionForm')
    const archiveForm = document.getElementById('archiveBulkForm')

    document.getElementById('bulkActionInput').value = action

    if (form) {
        document.getElementById('deleteReasonId').value = reason
        form.submit();
    }

    if (archiveForm) {
        archiveForm.submit();
    }
}

function submitSingleForm(action, url, reason) {
    const form = document.getElementById('delete-form-id')
    const singleDeleteReason = document.getElementById('input-delete-reason')
    const itemDeleteId = document.getElementById('input-delete-id')
    const typeAction = document.getElementById('input-delete-type')

    if (singleDeleteReason) {
        singleDeleteReason.value = reason
    }

    if (itemDeleteId) {
        itemDeleteId.value = modalState.ids
    }

    if (typeAction) {
        typeAction.value = action
    }

    form.action = url
    form.submit();

}