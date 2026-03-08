// helper
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

function handleStateAfterRemoval(data) {
    updateCartUI(data);
    const remainingItems = document.querySelectorAll('input[name="itemSelected"]').length;

    if (remainingItems === 0) {
        window.location.reload();
    } else {
        const checkAllLabel = document.querySelector('label[for="checkAll"]');
        if (checkAllLabel) {
            checkAllLabel.innerText = `Chọn tất cả (${remainingItems})`;
        }
    }
}

function executeSingleAction(event, url, element) {
    event.preventDefault();
    fetch(url, {
        method: 'GET',
        headers: {'X-Requested-With': 'XMLHttpRequest'}
    })
        .then(response => response.json())
        .then(data => {
            const listItem = element.closest('li');
            if (listItem) listItem.remove();

            handleStateAfterRemoval(data);
        })
        .catch(error => console.error('Lỗi khi thao tác:', error));
}

function executeBulkAction(event, url, errorMessage) {
    event.preventDefault();
    const checkedItems = document.querySelectorAll('input[name="itemSelected"]:checked');

    if (checkedItems.length === 0) {
        alert(errorMessage);
        return;
    }

    fetch(url, {
        method: 'GET',
        headers: {'X-Requested-With': 'XMLHttpRequest'}
    })
        .then(response => response.json())
        .then(data => {
            checkedItems.forEach(checkbox => {
                const listItem = checkbox.closest('li');
                if (listItem) listItem.remove();
            });

            handleStateAfterRemoval(data);
        })
        .catch(error => console.error('Lỗi thao tác hàng loạt:', error));
}
//

function updateSelectionAjax() {
    const form = document.getElementById('cartForm');

    fetch('update-select', {
        method: 'POST',
        headers: {'X-Requested-With': 'XMLHttpRequest'},
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
        headers: {'X-Requested-With': 'XMLHttpRequest'}
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

function deleteItemAjax(event, courseId, element) {
    executeSingleAction(event, `cart-manager?action=delete&id=${courseId}`, element);
}

function wishlistItemAjax(event, courseId, element) {
    executeSingleAction(event, `cart-manager?action=moveToWishlist&id=${courseId}`, element);
}

function removeSelectedAjax(event) {
    executeBulkAction(event, 'cart-manager?action=removeSelected', 'Vui lòng chọn ít nhất một khóa học để xóa!');
}

function wishlistSelectedAjax(event) {
    executeBulkAction(event, 'cart-manager?action=moveSelectedToWishlist', 'Vui lòng chọn ít nhất một khóa học để thêm vào Yêu thích!');
}