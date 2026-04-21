// này là để lọc danh mục
document.addEventListener('DOMContentLoaded', function () {
    let debounceTimer;
    const filterForm = document.getElementById('filterForm');
    const categoryTableBody = document.getElementById('categoryTableBody');

    if (!filterForm || !categoryTableBody) return;

    filterForm.addEventListener('input', function (e) {
        if (e.target.tagName === 'INPUT' || e.target.tagName === 'SELECT') {
            clearTimeout(debounceTimer);

            debounceTimer = setTimeout(() => {
                const formData = new FormData(filterForm);

                formData.append('renderType', 'partial');

                const params = new URLSearchParams(formData).toString();

                loadData(params);
            }, 500);
        }
    });

    function loadData(queryString) {
        categoryTableBody.style.opacity = '0.5';

        fetch(`admin/categories?${queryString}`, {
            method: 'GET',
            headers: {
                'X-Requested-With': 'XMLHttpRequest'
            }
        })
            .then(response => {
                if (!response.ok) throw new Error('Network response was not ok!');
                return response.text();
            })
            .then(html => {
                categoryTableBody.innerHTML = html;
                categoryTableBody.style.opacity = '1';
            })
            .catch(error => {
                console.error('Lỗi fetch: ', error);
                categoryTableBody.style.opacity = '1';
            });
    }

    // Ngăn submit reload trang
    filterForm.addEventListener('submit', (e) => e.preventDefault());
});


document.addEventListener('click', function (e) {
    const row = e.target.closest('.modern-table tbody tr');

    // Nếu click vào checkbox thì bỏ qua
    if (e.target.classList.contains('item-checkbox') || e.target.closest('.checkbox-wrapper')) {
        return;
    }

    // Nếu click vào button / link thì bỏ qua
    if (row && !e.target.closest('.icon-action-btn') && !e.target.closest('a') && !e.target.closest('button')) {

        const checkbox = row.querySelector('.item-checkbox');

        if (checkbox) {
            checkbox.checked = !checkbox.checked;

            checkbox.dispatchEvent(new Event('change', { bubbles: true }));
        }
    }
});