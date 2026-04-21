function toggleFilter() {
    const filterForm = document.getElementById('filterForm');

    // Toggle class 'collapsed'
    filterForm.classList.toggle('collapsed');

    // Lưu trạng thái vào localStorage để khi load lại trang không bị reset (Optional)
    const isCollapsed = filterForm.classList.contains('collapsed');
    localStorage.setItem('admin_filter_status', isCollapsed ? 'closed' : 'open');
}

// Khi vừa load trang, kiểm tra xem trước đó người dùng có đóng lọc không
document.addEventListener('DOMContentLoaded', function () {
    const status = localStorage.getItem('admin_filter_status');
    const filterForm = document.getElementById('filterForm');

    if (status === 'closed') {
        filterForm.classList.add('collapsed');
    }
});

function setupAutoFilter({formId, tableBodyId, url, deplay = 500}) {
    let debounceTimer = deplay
    const currentForm = document.getElementById(formId)
    const currentTableBody = document.getElementById(tableBodyId)

    currentForm.addEventListener('input', function (e) {
        if (e.target.tagName === 'INPUT' || e.target.tagName === 'SELECT') {
            clearTimeout(debounceTimer)

            debounceTimer = setTimeout(() => {
                const formData = new FormData(currentForm)
                formData.append("renderType", "partial")
                const params = new URLSearchParams(formData).toString()
                loadData(params)
            })
        }
    })

    function loadData(queryString) {
        currentTableBody.style.opacity = '0.5'

        fetch(`${url}?${queryString}`, {
            method: 'GET',
            header: {
                "X-Requested-With": "XMLHttpRequest"
            }
        })
            .then(response => {
                if (!response.ok) throw new Error('Network was not ok !')
                return response.text()
            })
            .then(html => {
                currentTableBody.innerHTML = html
                currentTableBody.style.opacity = '1'
            })
            .catch(err => {
                console.log(err)
                currentTableBody.style.opacity = '1'
            })
    }

    currentForm.addEventListener('submit', (e) => e.preventDefault())
}
