function toggleFilter() {
    const filterForm = document.getElementById('filterForm');

    // Toggle class 'collapsed'
    filterForm.classList.toggle('collapsed');

    // Lưu trạng thái vào localStorage để khi load lại trang không bị reset (Optional)
    const isCollapsed = filterForm.classList.contains('collapsed');
    localStorage.setItem('admin_filter_status', isCollapsed ? 'closed' : 'open');
}

// Khi vừa load trang, kiểm tra xem trước đó người dùng có đóng lọc không
document.addEventListener('DOMContentLoaded', function() {
    const status = localStorage.getItem('admin_filter_status');
    const filterForm = document.getElementById('filterForm');

    if (status === 'closed') {
        filterForm.classList.add('collapsed');
    }
});