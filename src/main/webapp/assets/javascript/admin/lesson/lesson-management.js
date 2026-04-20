document.addEventListener('DOMContentLoaded', function () {
    let debounceTimer;
    const filterForm = document.getElementById('filterForm');
    const lessonTableBody = document.getElementById('lessonTableBody');

    filterForm.addEventListener('input', function (e) {
        if (e.target.tagName === 'INPUT' || e.target.tagName === 'SELECT') {
            clearTimeout(debounceTimer)

            debounceTimer = setTimeout(() => {
                const formData = new FormData(filterForm)

                formData.append('renderType', 'partial');

                const params = new URLSearchParams(formData).toString();

                loadData(params)
            })
        }
    })

    function loadData(queryString) {
        lessonTableBody.style.opacity = '0.5';

        fetch(`admin/lessons?${queryString}`, {
            method: 'GET',
            headers: {
                'X-Requested-With': 'XMLHttpRequest'
            }
        })
            .then(response => {
                if (!response.ok) throw new Error('Network response was not ok !')
                return response.text()
            })
            .then(html => {
                lessonTableBody.innerHTML = html
                lessonTableBody.style.opacity = '1'
            })
            .catch(error => {
                console.error('Lỗi fetch: ', error)
                lessonTableBody.style.opacity = '1'
            })
    }

    filterForm.addEventListener('submit', (e) => e.preventDefault())
})

