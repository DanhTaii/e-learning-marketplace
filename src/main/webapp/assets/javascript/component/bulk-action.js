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
        const bulkActionForm = document.getElementById('bulkActionForm')
        const bulkActionInput = document.getElementById('bulkActionInput')

        if (!actionBar) return;

        //Lắng nghe nút sẽ được click trên thanh bulk
        actionBar.addEventListener('click', (e) => {
            //Xác nhận được người dùng bấm rồi thì xác nhận xem đó là hành động nào dựa trên data-action của nút đó
            const btn = e.target.closest('button[data-action]');
            if (!btn) return;

            //Lấy data-action của nút đó để gọi dến servlet
            const action = btn.getAttribute('data-action');
            //Lấy ra danh sách ID của đối tượng đó
            const ids = this.getSelectedIds()
            if (ids.length === 0) {
                alert("Vui lòng chọn ít nhất 1 mục!");
                return;
            }

            if (confirm(`Xác nhận ${action} ${ids.length} mục đã chọn?`)) {
                bulkActionInput.value = action
                bulkActionForm.submit()
            }
        })
    }
}

document.addEventListener('DOMContentLoaded', () => BulkActionManager.init());
