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
                const params = new URLSearchParams()

                for (const [key, value] of formData.entries()) {
                    // Chỉ thêm vào params nếu value không rỗng và không phải chỉ có khoảng trắng
                    if (value !== null && value.trim() !== '') {
                        params.append(key, value);
                    }
                }

                params.append("renderType", "partial");
                const queryString = params.toString();

                loadData(queryString)
            }, deplay)
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

                const params = new URLSearchParams(queryString);
                params.delete("renderType");

                const cleanQuery = params.toString();

                //Đưa lại param mới kèm thằng query
                const newUrl = window.location.pathname + (cleanQuery ? '?' + cleanQuery : '')
                window.history.replaceState({path: newUrl}, '', newUrl);

                //Gán giá trị PARAMS cho INPUT HIDDEN để bên server lấy
                const currentQueryInput = document.getElementById('currentQueryId')
                if (currentQueryInput) {
                    currentQueryInput.value = cleanQuery
                }

            })
            .catch(err => {
                console.log(err)
                currentTableBody.style.opacity = '1'
            })
    }

    currentForm.addEventListener('submit', (e) => e.preventDefault())
}
