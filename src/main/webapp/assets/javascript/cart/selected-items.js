function updateCartUI(data) {
    const formatCurrency = amount => new Intl.NumberFormat('vi-VN').format(amount) + " đ";


    document.getElementById('display-selected-qty').innerText = "Tổng cộng (" + data.selectedQuantity + "):";
    document.getElementById('display-final-price').innerText = formatCurrency(data.finalPriceTotal);
    document.getElementById('display-total-price').innerText = formatCurrency(data.total);


    const checkoutBtn = document.getElementById('checkout-btn');
    const checkoutLink = document.getElementById('checkout-link');

    if (data.selectedQuantity > 0) {
        checkoutBtn.disabled = false;
        checkoutLink.href = 'payment';
        checkoutLink.classList.add('turn-page');
    } else {
        checkoutBtn.disabled = true;
        checkoutLink.removeAttribute('href');
        checkoutLink.classList.remove('turn-page');
    }

    const checkAllBox = document.getElementById('checkAll');
    const totalItems = document.querySelectorAll('input[name="itemSelected"]').length;

    if (totalItems > 0 && data.selectedQuantity === totalItems) {
        checkAllBox.checked = true;
    } else {
        checkAllBox.checked = false;
    }
}

function updateSelectionAjax() {
    const form = document.getElementById('cartForm');

    fetch('update-select', {
        method: 'POST',
        headers: { 'X-Requested-With': 'XMLHttpRequest' },
        body: new URLSearchParams(new FormData(form))
    })
        .then(response => response.json())
        .then(data => {
            updateCartUI(data);
        })
        .catch(error => console.error('Lỗi khi cập nhật giỏ hàng:', error));
}

function handleSelectAll(checkbox) {
    const isChecked = checkbox.checked;

    fetch('cart-manager?action=selectAll&status=' + isChecked, {
        method: 'GET',
        headers: { 'X-Requested-With': 'XMLHttpRequest' }
    })
        .then(response => response.json())
        .then(data => {
            const itemCheckboxes = document.querySelectorAll('input[name="itemSelected"]');
            itemCheckboxes.forEach(cb => {
                cb.checked = isChecked;
            });

            updateCartUI(data);
        })
        .catch(error => console.error('Lỗi khi Chọn tất cả:', error));
}