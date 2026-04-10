function renderPagination(pageResponse, onPageChange) {
    const list = document.getElementById('admin-pagination-list');
    const tplItem = document.getElementById('tpl-pagination-item');
    const tplDots = document.getElementById('tpl-pagination-dots');
    const tplArrow = document.getElementById('tpl-pagination-arrow');

    if (!list || !tplItem || !tplDots || !tplArrow) return;

    const current = pageResponse.currentPage || 1;
    const total = pageResponse.totalPage || 0;

    // Clear UI cũ
    list.innerHTML = '';

    const btnPrev = tplArrow.content.cloneNode(true);
    const prevLink = btnPrev.querySelector('.js-page-arrow');

    btnPrev.querySelector('i').classList.add('fa-chevron-left');

    if (current === 1) {
        btnPrev.querySelector('.pagination-item')
            .classList.add('pagination-item--disabled');
    } else {
        prevLink.onclick = () => onPageChange(current - 1);
    }

    list.appendChild(btnPrev);


    const pages = getPaginationModel(current, total);

    pages.forEach(p => {
        if (p === '...') {
            list.appendChild(tplDots.content.cloneNode(true));
        } else {
            const item = tplItem.content.cloneNode(true);
            const link = item.querySelector('.js-page-number');

            link.textContent = p;

            if (p === current) {
                item.querySelector('.pagination-item')
                    .classList.add('pagination-item--active');
            } else {
                link.onclick = () => onPageChange(p);
            }

            list.appendChild(item);
        }
    });


    const btnNext = tplArrow.content.cloneNode(true);
    const nextLink = btnNext.querySelector('.js-page-arrow');

    btnNext.querySelector('i').classList.add('fa-chevron-right');

    if (current === total || total === 0) {
        btnNext.querySelector('.pagination-item')
            .classList.add('pagination-item--disabled');
    } else {
        nextLink.onclick = () => onPageChange(current + 1);
    }

    list.appendChild(btnNext);

    const infoText = document.getElementById('pagination-info');
    if (infoText) {
        infoText.textContent =
            `Trang ${current}/${total} - Tổng ${pageResponse.totalElement || 0} bản ghi`;
    }
}

function getPaginationModel(current, total) {
    const delta = 2;
    const range = [];
    const rangeWithDots = [];
    let last;

    for (let i = 1; i <= total; i++) {
        if (
            i === 1 ||
            i === total ||
            (i >= current - delta && i <= current + delta)
        ) {
            range.push(i);
        }
    }

    for (let i of range) {
        if (last) {
            if (i - last === 2) {
                rangeWithDots.push(last + 1);
            } else if (i - last > 1) {
                rangeWithDots.push('...');
            }
        }
        rangeWithDots.push(i);
        last = i;
    }

    return rangeWithDots;
}

function createPaginationLoader(url, renderData) {
    return function load(page = 1, size = 10) {
        fetch(`${url}?page=${page}&size=${size}`)
            .then(res => res.json())
            .then(data => {
                renderData(data.data); // render list
                renderPagination(data, load); // render pagination
            })
            .catch(err => console.error('Pagination load error:', err));
    };
}