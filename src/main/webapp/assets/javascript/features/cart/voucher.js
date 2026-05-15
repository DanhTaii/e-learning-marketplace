// Khi bấm nút "Dùng" ở một Voucher
function selectVoucher(code) {

    closeModal('voucherModal');

    fetch('apply-voucher', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: 'code=' + encodeURIComponent(code)
    })
        .then(res => res.json())
        .then(data => {
            if (data.status === 'success') {
                closeModal('voucherModal');

                // Hiện box báo đã chọn mã
                document.getElementById('applied-voucher-info').style.display = 'flex';
                document.getElementById('applied-voucher-code').innerText = "Mã: " + data.code;

                const finalPriceEl = document.getElementById('display-final-price');
                if(finalPriceEl) {
                    finalPriceEl.innerText = data.finalTotalFormatted;
                }

                toast({title: 'Áp dụng thành công!', message: successMessage, type: 'success', duration: 3000});
            } else {
                alert(data.message);
            }
        });
}

function applyManualVoucher() {
    const codeInput = document.getElementById('manualVoucherCode');
    const code = codeInput ? codeInput.value.trim() : '';

    if(code !== "") {
        selectVoucher(code);
    }
}

// Khi bấm nút "Bỏ chọn" mã
function removeVoucher(e) {
        if(e) e.preventDefault();

        fetch('remove-voucher', { method: 'POST' })
            .then(response => response.json())
            .then(data => {
                if (data.status === 'success') {
                    document.getElementById('applied-voucher-info').style.display = 'none';

                    const manualInput = document.getElementById('manualVoucherCode');
                    if (manualInput) manualInput.value = '';

                    const finalPriceEl = document.getElementById('display-final-price');
                    if (finalPriceEl) {
                        finalPriceEl.innerText = data.originalTotalFormatted;
                    }
                }
            });
}