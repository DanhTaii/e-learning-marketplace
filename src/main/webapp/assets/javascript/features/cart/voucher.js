// Khi bấm nút "Dùng" ở một Voucher
function selectVoucher(code, isReload = false) {
    closeModal('voucherModal');

    fetch('apply-voucher', {
        method: 'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded', 'X-CSRF-Token': getCsrfToken()},
        body: 'code=' + encodeURIComponent(code)
    })
        .then(res => res.json())
        .then(data => {
            if (data.status === 'success') {
                closeModal('voucherModal');

                // 1. Hiển thị box báo đã chọn mã ở phía trên
                const appliedBox = document.getElementById('applied-voucher-info');
                const appliedCode = document.getElementById('applied-voucher-code');
                if (appliedBox) appliedBox.style.display = 'flex';
                if (appliedCode) appliedCode.innerText = "Mã: " + data.code;

                // 2. Cập nhật dòng hiển thị số tiền giảm của Voucher trong Tóm tắt hóa đơn
                const voucherRow = document.getElementById('voucher-discount-row');
                const voucherLabel = document.getElementById('voucher-discount-label');
                const voucherAmount = document.getElementById('voucher-discount-amount');

                if (voucherRow && voucherLabel && voucherAmount) {
                    voucherLabel.innerHTML = `<i class="fa-solid fa-ticket"></i> Voucher (${data.code}):`;
                    voucherAmount.innerText = " - " + data.discountAmountFormatted;
                    voucherRow.style.display = 'flex';
                }

                const finalPriceEl = document.getElementById('display-final-price');
                if (finalPriceEl) {
                    finalPriceEl.innerText = data.finalTotalFormatted;
                }

                if (!isReload) {
                    toast({
                        title: 'Áp dụng thành công!',
                        message: 'Mã giảm giá đã được áp dụng',
                        type: 'success',
                        duration: 3000
                    });
                }
            } else {
                if (!isReload) {
                    alert(data.message);
                }
            }
        });
}

function applyManualVoucher() {
    const codeInput = document.getElementById('manualVoucherCode');
    const code = codeInput ? codeInput.value.trim() : '';

    if (code !== "") {
        selectVoucher(code);
    }
}

// Khi bấm nút "Bỏ chọn" mã
function removeVoucher(e) {
    if (e) e.preventDefault();

    fetch('remove-voucher', {
        method: 'POST',
        headers: {
            'X-CSRF-Token': getCsrfToken()
        }
    })
        .then(response => response.json())
        .then(data => {
            if (data.status === 'success') {
                const appliedBox = document.getElementById('applied-voucher-info');
                if (appliedBox) appliedBox.style.display = 'none';

                const manualInput = document.getElementById('manualVoucherCode');
                if (manualInput) manualInput.value = '';

                const voucherRow = document.getElementById('voucher-discount-row');
                if (voucherRow) {
                    voucherRow.style.display = 'none';
                }

                const finalPriceEl = document.getElementById('display-final-price');
                if (finalPriceEl) {
                    finalPriceEl.innerText = data.originalTotalFormatted;
                }
            }
        });
}

window.addEventListener('pageshow', function (event) {
    const hiddenInput = document.getElementById('savedVoucherCode');

    if (hiddenInput && hiddenInput.value.trim() !== '') {
        const savedCode = hiddenInput.value.trim();

        if (typeof selectVoucher === 'function') {
            selectVoucher(savedCode, true);
        }
    }
});