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
