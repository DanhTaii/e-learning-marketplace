document.addEventListener("DOMContentLoaded", function () {
    // Xử lý nút Danh Mục
    const browseBtn = document.querySelector(".header__browse");
    const browseDisplay = document.querySelector(".browse__display");

    if(browseBtn && browseDisplay) {
        browseBtn.addEventListener("click", function (e) {
            e.stopPropagation(); // Ngăn chặn bấm ra ngoài bị lỗi
            browseDisplay.classList.toggle("show");
            // Đóng menu Avatar nếu đang mở
            if(userDisplay) userDisplay.classList.remove("show");
        });
    }

    // Xử lý nút Avatar
    const userBtn = document.querySelector(".header__user");
    const userDisplay = document.querySelector(".user__display");

    if(userBtn && userDisplay) {
        userBtn.addEventListener("click", function (e) {
            e.stopPropagation();
            userDisplay.classList.toggle("show");
            // Đóng menu Danh mục nếu đang mở
            if(browseDisplay) browseDisplay.classList.remove("show");
        });
    }

    // Bấm ra ngoài màn hình thì tự động đóng tất cả menu
    document.addEventListener("click", function () {
        if(browseDisplay) browseDisplay.classList.remove("show");
        if(userDisplay) userDisplay.classList.remove("show");
    });
});