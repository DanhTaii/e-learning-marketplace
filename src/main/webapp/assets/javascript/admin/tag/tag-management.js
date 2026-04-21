document.addEventListener('DOMContentLoaded', function () {

    let debounceTimer;
    const filterForm = document.getElementById('filterForm');
    const tagTableBody = document.getElementById('tagTableBody');

    if (!filterForm || !tagTableBody) return;

    filterForm.addEventListener('input', function (e) {
        if (e.target.tagName === 'INPUT' || e.target.tagName === 'SELECT') {

            clearTimeout(debounceTimer);

            debounceTimer = setTimeout(() => {

                const formData = new FormData(filterForm);

                // ⚡ báo controller render fragment
                formData.append('renderType', 'partial');

                const params = new URLSearchParams(formData).toString();

                loadData(params);

            }, 500);
        }
    });

    function loadData(queryString) {

        tagTableBody.style.opacity = '0.5';

        fetch(`admin/tags?${queryString}`, {
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
                tagTableBody.innerHTML = html;
                tagTableBody.style.opacity = '1';
            })
            .catch(error => {
                console.error('Lỗi fetch: ', error);
                tagTableBody.style.opacity = '1';
            });
    }

    filterForm.addEventListener('submit', (e) => e.preventDefault());
});


document.addEventListener('click', function (e) {

    const row = e.target.closest('.modern-table tbody tr');

    if (e.target.classList.contains('item-checkbox') || e.target.closest('.checkbox-wrapper')) {
        return;
    }

    if (row &&
        !e.target.closest('.icon-action-btn') &&
        !e.target.closest('a') &&
        !e.target.closest('button')) {

        const checkbox = row.querySelector('.item-checkbox');

        if (checkbox) {
            checkbox.checked = !checkbox.checked;

            checkbox.dispatchEvent(new Event('change', { bubbles: true }));
        }
    }
});