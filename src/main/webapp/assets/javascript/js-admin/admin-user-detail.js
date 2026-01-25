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
            document.getElementById('detail-username').value = user.username || 'Chưa cập nhật';
            document.getElementById('detail-email').value = user.email;
            document.getElementById('detail-phone').value = user.phone || 'Chưa cập nhật';

            const roleSelect = document.getElementById('detail-role');
            const roleValue = user.role ? user.role.toLowerCase() : 'user';
            roleSelect.value = roleValue;

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

function updateUser(event) {
    event.preventDefault();

    const formData = new FormData(document.getElementById('updateUserForm'));
    const params = new URLSearchParams(formData);

    fetch('admin/user/detail', {
        method: 'POST',
        body: params,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    })
        .then(response => {
            if (response.ok) {
                alert("Cập nhật thành công!");
                location.reload();
            } else {
                // Nếu Server trả về lỗi 400, 500...
                alert("Cập nhật thất bại! Vui lòng kiểm tra lại Server.");
            }
        })
        .catch(error => {
            console.error('Lỗi kết nối:', error);
            alert("Không thể kết nối đến máy chủ!");
        });
}

// Hàm đóng modal
function closeModal() {
// 1. Tìm tất cả các thẻ có class là modal hoặc modal__course-detail
    const modals = document.querySelectorAll('.modal, .modal__course-detail');

    // 2. Duyệt qua từng thằng và ẩn nó đi
    modals.forEach(modal => {
        modal.style.display = 'none';
    });
}

// Đóng khi click ra ngoài vùng modal
window.onclick = function (event) {
    if (event.target.classList.contains('modal__course-detail') || event.target.classList.contains('modal')) {
        event.target.style.display = 'none';
    }
}

let isDirty = false; // Mặc định là chưa có thay đổi

// 1. Khi người dùng nhập bất cứ thứ gì, đánh dấu là đã thay đổi
// Nên áp dụng cho toàn bộ form trong modal
document.querySelectorAll('#updateUserForm input, #updateUserForm select, #updateUserForm textarea').forEach(item => {
    item.addEventListener('input', () => {
        isDirty = true;
    });
});

// 2. Sửa lại hàm closeModal để kiểm tra cái "Cờ" này
function closeModal() {
    if (isDirty) {
        // Hiện thông báo xác nhận kiểu cũ (trình duyệt)
        const confirmLeave = confirm("Bạn có thay đổi chưa lưu. Bạn có chắc chắn muốn thoát không?");
        if (!confirmLeave) {
            return; // Nếu chọn "Hủy" (không thoát) thì dừng lại
        }
    }

    // Nếu không có thay đổi hoặc user chấp nhận bỏ qua -> Đóng modal
    const modals = document.querySelectorAll('.modal, .modal__course-detail');
    modals.forEach(modal => {
        modal.style.display = 'none';
    });
    isDirty = false; // Reset lại trạng thái cho lần sau
}