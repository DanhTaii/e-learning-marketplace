function openModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) modal.style.display = 'flex';
}

function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) modal.style.display = 'none';
}

window.addEventListener('click', function (event) {
    if (event.target.classList.contains('modal')) {
        event.target.style.display = 'none';
        if (event.target.id === 'confirm-delete-modal') currentDeleteId = null;
    }
});

let currentDeleteId = null;

function openConfirmModal(id, modalId = 'confirm-delete-modal') {
    currentDeleteId = id;
    document.getElementById('input-delete-id').value = id;
    openModal(modalId);
}

document.addEventListener('DOMContentLoaded', function() {
    const confirmDeleteBtn = document.getElementById('btn-confirm-delete');
    if (confirmDeleteBtn) {
        confirmDeleteBtn.addEventListener('click', function () {
            if (currentDeleteId) {
                const form = document.getElementById('delete-form-id');
                if (form) form.submit();
            }
        });
    }
});