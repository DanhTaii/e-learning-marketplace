function showCategoryDetail(id) {
    fetch("admin/category/update?id=" + id)
        .then(response => {
            if (!response.ok) throw new Error("Lỗi mạng hoặc không tìm thấy ID");
            return response.json(); // Bước quan trọng: Chuyển response sang JSON
        })
        .then(cate => { // Lúc này 'cate' mới là dữ liệu thật từ Servlet
            // Lưu ý: modal-title là thẻ SPAN (dùng innerText), không phải input (value)
            document.getElementById('modal-title').innerText = 'THÔNG TIN: ' + (cate.name || "");

            document.getElementById('detail-id').value = cate.id;
            document.getElementById('detail-slug').value = cate.slug || "";
            document.getElementById('detail-parentId').value = cate.parentId || "";
            document.getElementById('detail-icon').value = cate.icon || "";

            if (cate.createdAt) {
                document.getElementById('detail-created').value = new Date(cate.createdAt).toLocaleDateString("vi-VN");
            }

            if (cate.updatedAt) {
                // Sửa ID đúng cho ngày cập nhật
                document.getElementById('detail-updated').value = new Date(cate.updatedAt).toLocaleDateString("vi-VN");
            }

            document.getElementById('category-detail').style.display = 'flex';
        })
        .catch(error => {
            console.error("Lỗi:", error);
            alert("Không thể lấy dữ liệu danh mục!");
        });
}

function closeModal() {
    document.getElementById('category-detail').style.display = 'none';
}

window.onclick = function (event) {
    let modal = document.getElementById('category-detail');
    if (event.target == modal) {
        closeModal();
    }
}