document.querySelectorAll(".tab-item").forEach(tab => {
    tab.addEventListener("click", function () {
        const target = this.dataset.tab;

        // active tab header
        document.querySelectorAll(".tab-item").forEach(t => t.classList.remove("active"));
        this.classList.add("active");

        // show content
        document.querySelectorAll(".tab-pane").forEach(p => p.classList.remove("active"));
        document.getElementById(target).classList.add("active");
    });
});


// Bắt sự kiện khi người dùng chọn file mới ở ô input
document.getElementById('thumbnail-file').addEventListener('change', function (event) {

    // Lấy ra file đầu tiên mà người dùng vừa chọn
    const file = event.target.files[0];

    // Kiểm tra xem người dùng có thực sự chọn file không
    if (file) {
        // Tạo một đường dẫn ảo để trình duyệt có thể đọc được file này
        const tempUrl = URL.createObjectURL(file);

        // Tìm đến thẻ img dùng để preview và thay đổi thuộc tính src
        const previewImg = document.getElementById('image-preview');
        previewImg.src = tempUrl;
    }
});
