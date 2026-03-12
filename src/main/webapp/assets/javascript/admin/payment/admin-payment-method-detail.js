function showPaymentMethodDetail(id) {
    console.log("Đã bấm nút Sửa cho ID:", id);

    fetch('admin/payment-methods/detail?id=' + id)
        .then(response => {
            if (!response.ok) throw new Error('Network response was not ok');
            return response.json();
        })
        .then(pm => {
            console.log("Dữ liệu nhận về:", pm);

            const pmName = pm.name || "KHÔNG TÊN";
            document.getElementById('modal-title').innerText = "CẬP NHẬT PHƯƠNG THỨC: " + pmName.toUpperCase();

            document.getElementById('detail-id').value = pm.id;
            document.getElementById('detail-name').value = pm.name || "";
            document.getElementById('detail-code').value = pm.code || "";
            document.getElementById('detail-iconUrl').value = pm.iconUrl || "";

            const statusSelect = document.getElementById('detail-status');
            if (pm.status === 'ACTIVE') {
                statusSelect.value = 'ACTIVE';
            } else {
                statusSelect.value = 'INACTIVE';
            }

            if (pm.createdAt) {
                document.getElementById('detail-created').value = new Date(pm.createdAt).toLocaleString('vi-VN');
            }

            if (pm.updatedAt) {
                document.getElementById('detail-updated').value = new Date(pm.updatedAt).toLocaleString('vi-VN');
            } else {
                document.getElementById('detail-updated').value = "Chưa có cập nhật";
            }

            document.getElementById('payment-method-detail').style.display = 'flex';
        })
        .catch(error => {
            console.error('Lỗi khi fetch dữ liệu:', error);
        });
}

function closePaymentMethodModal() {
    document.getElementById('payment-method-detail').style.display = 'none';
}

// Đóng khi click ra ngoài - giống hệt Tag
window.onclick = function (event) {
    let modal = document.getElementById('payment-method-detail');
    if (event.target === modal) {
        closePaymentMethodModal();
    }
}