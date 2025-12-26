function showUserDetail(id) {
    // 1. Gọi đến Servlet để lấy thông tin
    // const contextPath = '${pageContext.request.contextPath}';
    fetch('admin/user/detail?id=' + id)
        .then(response => {
            if (!response.ok) throw new Error('Mạng có vấn đề');
            return response.json();
        })
        .then(user => {
            // 2. Điền dữ liệu vào các thẻ input trong Modal
            //Thằng này là span (text thuần) nên điền innerText
            document.getElementById('modal-title').innerText = "THÔNG TIN: " + user.username.toUpperCase();
            //Những thằng dưới này là input nên điền value
            document.getElementById('detail-id').value = user.id;
            document.getElementById('detail-username').value = user.username;
            document.getElementById('detail-email').value = user.email;
            document.getElementById('detail-phone').value = user.phone || 'Chưa cập nhật';
            document.getElementById('detail-role').value = user.role;

            if (user.updatedAt) {
                let updatedAt = new Date(user.updatedAt);
                document.getElementById('detail-updated').value = updatedAt.toLocaleDateString('vi-VN');
            }

            // Định dạng ngày tháng (user.createdAt thường là timestamp)
            if (user.createdAt) {
                let createdAt = new Date(user.createdAt);
                document.getElementById('detail-created').value = createdAt.toLocaleDateString('vi-VN');
            }

            // 3. Hiển thị modal
            document.getElementById('user-detail').style.display = 'flex';
        })
        .catch(error => {
            console.error('Lỗi fetch:', error);
            alert('Không thể lấy thông tin người dùng!');
        });
}

// Hàm đóng modal
function closeModal() {
    document.getElementById('user-detail').style.display = 'none';
}

// Đóng khi click ra ngoài vùng modal
window.onclick = function (event) {
    let modal = document.getElementById('user-detail');
    if (event.target == modal) {
        closeModal();
    }
}