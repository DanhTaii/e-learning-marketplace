// Khi bấm nút "Dùng" ở một Voucher
function selectVoucher(code) {
    // 1. Tận dụng hàm closeModal của bạn để đóng popup
    closeModal('voucherModal');

    // 2. Hiển thị UI đã chọn mã ở ngoài Giỏ hàng
    const infoBox = document.getElementById('applied-voucher-info');
    const codeSpan = document.getElementById('applied-voucher-code');

    if (infoBox && codeSpan) {
        infoBox.style.display = 'flex';
        codeSpan.innerText = "Mã: " + code;
    }

    // 3. TODO: Gọi AJAX gửi 'code' xuống server để tính lại tổng tiền
    // applyDiscountAjax(code);
}

// Khi nhập mã thủ công và bấm Áp dụng
function applyManualVoucher() {
    const codeInput = document.getElementById('manualVoucherCode');
    const code = codeInput ? codeInput.value.trim() : '';

    if(code !== "") {
        selectVoucher(code);
    }
}

// Khi bấm nút "Bỏ chọn" mã
function removeVoucher(e) {
    e.preventDefault();
    document.getElementById('applied-voucher-info').style.display = 'none';

    const manualInput = document.getElementById('manualVoucherCode');
    if (manualInput) manualInput.value = '';

    // TODO: Gọi AJAX hủy mã ở đây để backend tính lại giá gốc
    // removeDiscountAjax();
}