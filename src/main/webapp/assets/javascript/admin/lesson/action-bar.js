document.addEventListener('DOMContentLoaded', function () {
    const selectAll = document.getElementById('selectAll');
    const checkboxes = document.querySelectorAll('.lesson-checkbox');
    const actionBar = document.getElementById('actionBar');
    const selectedCount = document.getElementById('selectedCount');

    // Hàm cập nhật trạng thái thanh Action Bar
    function updateActionBar() {
        const checkedCount = document.querySelectorAll('.lesson-checkbox:checked').length;
        if (checkedCount > 0) {
            actionBar.style.display = 'flex'; // Hiện thanh bar
            selectedCount.innerText = checkedCount;
        } else {
            actionBar.style.display = 'none'; // Ẩn thanh bar
        }
    }

    // Sự kiện cho nút "Chọn tất cả"
    if (selectAll) {
        selectAll.addEventListener('change', function () {
            checkboxes.forEach(cb => {
                cb.checked = this.checked;
            });
            updateActionBar();
        });
    }

    // Sự kiện cho từng checkbox lẻ (Dùng Event Delegation để chắc chắn ăn)
    document.querySelector('.modern-table tbody').addEventListener('change', function (e) {
        if (e.target.classList.contains('lesson-checkbox')) {
            updateActionBar();

            // Nếu bỏ chọn 1 cái thì nút "Chọn tất cả" cũng phải bỏ chọn theo
            if (!e.target.checked && selectAll) {
                selectAll.checked = false;
            }
        }
    });
});

// Hàm bỏ chọn tất cả (Nút X trên thanh Bar)
function deselectAll() {
    const selectAll = document.getElementById('selectAll');
    if (selectAll) selectAll.checked = false;

    document.querySelectorAll('.lesson-checkbox').forEach(cb => {
        cb.checked = false;
    });
    document.getElementById('actionBar').style.display = 'none';
}