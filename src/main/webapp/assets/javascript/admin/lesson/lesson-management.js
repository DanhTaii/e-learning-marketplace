// ======================= XỬ LÝ GỬI YÊU CẦU LỌC BÀI HỌC =======================
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
            }, 500)
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

// ======================= CHỌN HÀNG LÀ BẤM CẢ TÍCH =======================
document.addEventListener('click', function (e) {
    const row = e.target.closest('.modern-table tbody tr')


    if (e.target.classList.contains('item-checkbox') || e.target.closest('.checkbox-wrapper')) {
        return;
    }

    //Chắc chắn nó là hàng, nó phải k là thẻ a, button và không là icon-action-btn
    if (row && !e.target.closest('.icon-action-btn') && !e.target.closest('a') && !e.target.closest('button')) {

        //Lấy ra cái thằng đó
        const checkbox = row.querySelector('.item-checkbox');

        if (checkbox) {
            checkbox.checked = !checkbox.checked;

            checkbox.dispatchEvent(new Event('change', {bubbles: true}))
        }
    }
})
