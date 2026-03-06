function renderPagination(current, total, totalElements, callbackName) {
    const list = document.getElementById('admin-pagination-list');
    const tplItem = document.getElementById('tpl-pagination-item');
    const tplDots = document.getElementById('tpl-pagination-dots');
    const tplArrow = document.getElementById('tpl-pagination-arrow');

    // Trả về luôn nếu không có phân trnag
    if (!list || !tplItem || !tplDots || !tplArrow) return;

    list.innerHTML = ''; // Xóa sạch thanh cũ

    // 1. Nút quay lại (Arrow Left)
    const btnPrev = tplArrow.content.cloneNode(true);
    const prevLink = btnPrev.querySelector('.js-page-arrow');
    btnPrev.querySelector('i').classList.add('fa-chevron-left');

    if (current === 1) {
        btnPrev.querySelector('.pagination-item').classList.add('pagination-item--disabled');
    } else {
        // Dùng window[callbackName] để gọi hàm động
        prevLink.onclick = () => window[callbackName](current - 1);
    }
    list.appendChild(btnPrev);

    // 2. Thuật toán lấy danh sách số trang cần hiển thị
    const pages = getPaginationModel(current, total)
    pages.forEach(p => {
        if (p === '...') {
            const dots = tplDots.content.cloneNode(true);
            list.appendChild(dots);
        } else {
            const item = tplItem.content.cloneNode(true);
            const link = item.querySelector('.js-page-number');
            // Dùng textContent chống XSS tuyệt đối
            link.textContent = p;

            if (p === current) {
                item.querySelector('.pagination-item').classList.add('pagination-item--active');
            } else {
                link.onclick = () => window[callbackName](p);
            }
            list.appendChild(item);
        }
    })

    // 3. Nút tới (Arrow Right)
    const btnNext = tplArrow.content.cloneNode(true);
    const nextLink = btnNext.querySelector('.js-page-arrow');
    btnNext.querySelector('i').classList.add('fa-chevron-right');

    if (current === total || total === 0) {
        btnNext.querySelector('.pagination-item').classList.add('pagination-item--disabled');
    } else {
        // Gọi hàm động theo tên được truyền vào
        nextLink.onclick = () => window[callbackName](current + 1);
    }
    list.appendChild(btnNext);

    // // Cập nhật text thông tin
    // if (infoText) {
    //     infoText.textContent = `Hiển thị trang ${current}/${total} (Tổng số ${totalElements})`;
    // }
}

function getPaginationModel(current, total) {
    const delta = 2;
    const range = [];
    const rangeWithDots = [];
    let l;

    for (let i = 1; i <= total; i++) {
        if (i === 1 || i === total || (i >= current - delta && i <= current + delta)) {
            range.push(i);
        }
    }

    for (let i of range) {
        if (l) {
            if (i - l === 2) {
                rangeWithDots.push(l + 1);
            } else if (i - l !== 1) {
                rangeWithDots.push('...');
            }
        }
        rangeWithDots.push(i);
        l = i;
    }
    return rangeWithDots;
}
