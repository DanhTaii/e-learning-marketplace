// Đợi DOM load xong mới gán sự kiện
document.addEventListener('DOMContentLoaded', function () {
    const downloadBtn = document.getElementById('btn-download-img');

    if (downloadBtn) {
        downloadBtn.addEventListener('click', function () {
            const btn = this;
            const originalHtml = btn.innerHTML;

            // 1. LẤY MÃ CHỨNG CHỈ TỪ DATA ATTRIBUTE
            const certCode = btn.getAttribute('data-cert-code') || 'DEFAULT-CODE';

            // 2. Cập nhật UX thành trạng thái Loading
            btn.innerHTML = '<i class="fa-solid fa-spinner fa-spin"></i> Đang xử lý...';
            btn.disabled = true;

            // ĐẢM BẢO TOÀN BỘ FONT ĐÃ LOAD XONG MỚI TIẾN HÀNH CHỤP
            document.fonts.ready.then(function () {
                const certElement = document.getElementById('certificate-preview');
                const rect = certElement.getBoundingClientRect();

                // TRÍCH XUẤT FONT CHÍNH XÁC TỪ ELEMENT ĐANG HIỂN THỊ
                const originalBrandName = certElement.querySelector('.brand-name');
                const exactFontFamily = originalBrandName ? window.getComputedStyle(originalBrandName).fontFamily : window.getComputedStyle(document.body).fontFamily;

                // 3. TẠO WRAPPER
                const wrapper = document.createElement('div');
                wrapper.style.fontFamily = exactFontFamily;
                wrapper.style.position = 'fixed';
                wrapper.style.top = '-9999px';
                wrapper.style.left = '0';
                wrapper.style.width = rect.width + 'px';
                wrapper.style.padding = '0px';
                wrapper.style.backgroundColor = '#ffffff';
                wrapper.style.boxSizing = 'content-box';

                // 4. Cấu hình bản clone
                const cloneNode = certElement.cloneNode(true);
                cloneNode.style.margin = '0';
                cloneNode.style.width = '100%';
                cloneNode.style.height = 'auto';
                cloneNode.style.transform = 'none';

                // 5. FIX LỖI FONT VÀ NHOÈ VIỀN CHO CÁC TIÊU ĐỀ
                const brandNames = cloneNode.querySelectorAll('.brand-name');
                brandNames.forEach(el => {
                    el.style.fontFamily = exactFontFamily;
                    el.style.letterSpacing = '2px';
                    el.style.fontVariantLigatures = 'none';
                    el.style.WebkitFontSmoothing = 'antialiased';
                    el.style.MozOsxFontSmoothing = 'grayscale';
                    el.style.textRendering = 'geometricPrecision';
                });

                const brandSubs = cloneNode.querySelectorAll('.brand-sub');
                brandSubs.forEach(el => {
                    el.style.fontFamily = exactFontFamily;
                    el.style.letterSpacing = '4px';
                    el.style.WebkitFontSmoothing = 'antialiased';
                    el.style.MozOsxFontSmoothing = 'grayscale';
                    el.style.textRendering = 'geometricPrecision';
                });

                // Lắp ráp vào DOM
                wrapper.appendChild(cloneNode);
                document.body.appendChild(wrapper);

                // Delay một chút để trình duyệt kịp apply CSS mới cho bản clone
                setTimeout(() => {
                    // 6. Tiến hành chụp Wrapper
                    html2canvas(wrapper, {
                        scale: 2,
                        useCORS: true,
                        backgroundColor: "#ffffff",
                        logging: false
                    }).then(function (canvas) {
                        // Khởi tạo link tải về
                        const imgData = canvas.toDataURL('image/png');
                        const link = document.createElement('a');

                        // SỬ DỤNG BIẾN certCode ĐÃ LẤY Ở BƯỚC 1
                        link.download = `Chung-Chi-${certCode}.png`;
                        link.href = imgData;
                        link.click();

                        // 7. Dọn dẹp DOM tránh rác
                        document.body.removeChild(wrapper);

                        // Khôi phục nút bấm
                        btn.innerHTML = originalHtml;
                        btn.disabled = false;

                    }).catch(function (error) {
                        console.error("Lỗi khi tạo ảnh: ", error);
                        alert("Đã xảy ra lỗi khi tạo ảnh. Vui lòng thử lại!");

                        // Fallback dọn dẹp nếu có lỗi
                        if (document.body.contains(wrapper)) {
                            document.body.removeChild(wrapper);
                        }
                        btn.innerHTML = originalHtml;
                        btn.disabled = false;
                    });
                }, 300); // Delay 300ms
            });
        });
    }
});