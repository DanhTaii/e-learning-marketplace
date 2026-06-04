document.addEventListener("DOMContentLoaded", function () {
    const btn = document.querySelector(".verify-search-button");
    const input = document.getElementById("certificateCode");

    const emptyState = document.getElementById("emptyState");
    const successState = document.getElementById("successState");
    const errorState = document.getElementById("errorState");
    const errorMessage = document.getElementById("error-message");

    btn.addEventListener("click", async function () {
        const code = input.value.trim();

        // Đặt lại trạng thái ẩn cho tất cả
        emptyState.style.display = 'none';
        successState.style.display = 'none';
        errorState.style.display = 'none';

        if (code === '') {
            emptyState.style.display = 'block';
            return;
        }

        // Bật trạng thái loading
        btn.innerHTML = '<i class="fa-solid fa-spinner fa-spin"></i> <span>Đang xử lý...</span>';
        btn.disabled = true;

        try {
            const response = await fetch('certificate/verify', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'X-CSRF-Token': getCsrfToken()
                },
                body: new URLSearchParams({'code': code})
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.error || 'Có lỗi xảy ra trong quá trình xác minh.');
            }

            // Lấy dữ liệu JSON từ Servlet trả về
            const data = await response.json();

            document.getElementById('cert-code').innerText = data.certificateCode || code;
            document.getElementById('cert-student').innerText = (data.firstName + ' ' + data.lastName) || 'N/A';
            document.getElementById('cert-course').innerText = data.courseName || 'N/A';
            document.getElementById('cert-issue-date').innerText = data.issueDate || 'N/A';

            // Mở block Success
            successState.style.display = 'block';

        } catch (error) {
            console.error("Lỗi xác minh:", error);
            errorMessage.innerText = error.message;
            errorState.style.display = 'block';
        } finally {
            btn.innerHTML = '<i class="fa-solid fa-magnifying-glass"></i><span>Xác minh</span>';
            btn.disabled = false;
        }
    });

    //Đọc tham số trên URL
    const urlParams = new URLSearchParams(window.location.search);
    const codeFromUrl = urlParams.get('code');

    //Nếu trên URL có mã (code)
    if (codeFromUrl) {
        // Tự động điền mã vào ô input
        input.value = codeFromUrl;
        // Tự động kích hoạt sự kiện bấm nút "Xác minh"
        btn.click();
    }
});