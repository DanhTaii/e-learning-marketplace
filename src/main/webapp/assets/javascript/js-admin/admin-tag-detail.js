function showTagDetail(id) {
    console.log("Đã bấm nút Sửa cho ID:", id); // Kiểm tra xem nút có ăn lệnh không

    fetch('admin/tag/detail?id=' + id)
        .then(response => {
            if (!response.ok) throw new Error('Network response was not ok');
            return response.json();
        })
        .then(tag => {
            console.log("Dữ liệu nhận về:", tag); // Kiểm tra các field name/slug có đúng không

            // Bảo vệ tránh lỗi toUpperCase() khi name bị null
            const tagName = tag.name || tag.nameTag || "KHÔNG TÊN";
            document.getElementById('modal-title').innerText = "CẬP NHẬT THẺ: " + tagName.toUpperCase();

            // Gán giá trị (Dùng dấu || "" để tránh hiện chữ 'undefined' trong ô input)
            document.getElementById('detail-id').value = tag.id;
            document.getElementById('detail-nameTag').value = tag.name || tag.nameTag || "";
            document.getElementById('detail-slugTag').value = tag.slug || tag.slugTag || "";

            // Xử lý ngày tháng
            if (tag.createdAt) {
                document.getElementById('detail-created').value = new Date(tag.createdAt).toLocaleString('vi-VN');
            }

            if (tag.updatedAt) {
                document.getElementById('detail-updated').value = new Date(tag.updatedAt).toLocaleString('vi-VN');
            } else {
                document.getElementById('detail-updated').value = "Chưa có cập nhật";
            }

            // HIỂN THỊ MODAL
            document.getElementById('tag-detail').style.display = 'flex';

        })
        .catch(error => {
            // console.error('Lỗi khi fetch dữ liệu:', error);
            // Gọi toast báo lỗi
            toast({ title: 'Lỗi!', message: 'Không thể lấy thông tin thẻ', type: 'error' });
        });
}
function closeModal() {
    document.getElementById('tag-detail').style.display = 'none';
}

// Đóng khi click ra ngoài vùng modal
window.onclick = function (event) {
    let modal = document.getElementById('tag-detail');
    if (event.target == modal) {
        closeModal();
    }
}