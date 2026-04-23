//============================= XỬ LÝ SỰ KIỆN BULK-ACTION-BAR =============================
const BulkActionManager = {
    //Dùng để lấy ra danh sách ID các bài học được chọn
    getSelectedIds: function () {
        //Bước này là bước tìm kiếm thì nó sẽ tìm tất cả giá trị của các checkbox có class là item-checkbox
        //Nhưng các checkbox này phải được checked
        const checkNodes = document.querySelectorAll('.item-checkbox:checked')
        return Array.from(checkNodes)
            //cb chỉ là đặt tên biến thôi, thì cb.value sẽ trả về giá trị của checkbox
            //Ví dụ bên JSP có giá trị là ${lesson.id} thì nó trả về giá trị của lesson.id
            .map(cb => cb.value);
    },

    init: function (callbacks) {
        const actionBar = document.getElementById('actionBar');

        if (!actionBar) return;

        //Lắng nghe nút sẽ được click trên thanh bulk
        document.addEventListener('click', (e) => {
            //Xác nhận được người dùng bấm rồi thì xác nhận xem đó là hành động nào dựa trên data-action của nút đó
            //Tìm ra phần tử cha hoặc của chính nó mà gần nhất với selector
            //dưới này phải tìm là button có data-action và nằm trong #actionBar
            const btn = e.target.closest('#actionBar button[data-action]');
            if (!btn) return;

            //Lấy data-action của nút đó để gọi dến servlet
            const action = btn.getAttribute('data-action');
            //Lấy ra danh sách ID của đối tượng đó
            const ids = this.getSelectedIds()

            setupConfirmModal({
                action: action,
                ids: ids,
                isBulk: true,
                count: ids.length
            });

        });

    },

}

document.addEventListener('DOMContentLoaded', () => BulkActionManager.init());

//============================= XỬ LÝ UI BULK-ACTION-BAR =============================

// Lắng nghe sự kiện change trên toàn bộ trang
document.addEventListener('change', function (e) {
    // Xử lý cho các checkbox lẻ (.item-checkbox)
    if (e.target.classList.contains('item-checkbox')) {
        updateActionBar();

        // Đồng bộ nút Select All (nếu bỏ chọn 1 cái thì nút tổng cũng bỏ)
        const selectAll = document.getElementById('selectAll');
        if (!e.target.checked && selectAll) selectAll.checked = false;
    }

    //Xử lý cho nút "Chọn tất cả" (#selectAll)
    if (e.target.id === 'selectAll') {
        const allCheckboxes = document.querySelectorAll('.item-checkbox');
        allCheckboxes.forEach(cb => cb.checked = e.target.checked);
        updateActionBar();
    }
});

// Hàm cập nhật trạng thái thanh Bar (Luôn đếm lại từ đầu)
function updateActionBar() {
    const actionBar = document.getElementById('actionBar');
    const selectedCount = document.getElementById('selectedCount');
    if (!actionBar) return;

    const checkedCount = document.querySelectorAll('.item-checkbox:checked').length;

    if (checkedCount > 0) {
        actionBar.style.display = 'flex';
        if (selectedCount) selectedCount.innerText = checkedCount;
    } else {
        actionBar.style.display = 'none';
    }
}

function deselectAll() {
    const selectAll = document.getElementById('selectAll');
    if (selectAll) selectAll.checked = false;

    document.querySelectorAll('.item-checkbox').forEach(cb => {
        cb.checked = false;
    });

    const actionBar = document.getElementById('actionBar');
    if (actionBar) actionBar.style.display = 'none';
}