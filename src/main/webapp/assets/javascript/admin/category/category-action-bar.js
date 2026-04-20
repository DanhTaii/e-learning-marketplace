document.addEventListener('DOMContentLoaded', function () {

    const selectAll = document.getElementById('selectAll');
    const checkboxes = document.querySelectorAll('.category-checkbox');
    const actionBar = document.getElementById('actionBar');
    const selectedCount = document.getElementById('selectedCount');

    // ===== Update Action Bar =====
    function updateActionBar() {
        const checkedCount = document.querySelectorAll('.category-checkbox:checked').length;

        if (checkedCount > 0) {
            actionBar.style.display = 'flex';
            selectedCount.innerText = checkedCount;
        } else {
            actionBar.style.display = 'none';
        }
    }

    // ===== Select All =====
    if (selectAll) {
        selectAll.addEventListener('change', function () {
            checkboxes.forEach(cb => {
                cb.checked = this.checked;
            });
            updateActionBar();
        });
    }

    // ===== Checkbox từng dòng (Event Delegation) =====
    const tableBody = document.querySelector('.modern-table tbody');

    if (tableBody) {
        tableBody.addEventListener('change', function (e) {
            if (e.target.classList.contains('category-checkbox')) {
                updateActionBar();

                // Nếu bỏ chọn 1 cái → bỏ luôn selectAll
                if (!e.target.checked && selectAll) {
                    selectAll.checked = false;
                }
            }
        });
    }

});

// ===== Nút X: bỏ chọn tất cả =====
function deselectAll() {
    const selectAll = document.getElementById('selectAll');
    if (selectAll) selectAll.checked = false;

    document.querySelectorAll('.category-checkbox').forEach(cb => {
        cb.checked = false;
    });

    document.getElementById('actionBar').style.display = 'none';
}