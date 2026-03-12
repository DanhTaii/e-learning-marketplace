function loadAdminCourses(page = 1) {
    // Lấy dữ liệu từ các ô nhập liệu
    const courseTitle = document.querySelector('input[name="courseTitle"]').value;
    const dateFrom = document.querySelector('input[name="dateFrom"]').value;
    const isPublic = document.querySelector('select[name="isPublic"]').value;
    const level = document.querySelector('select[name="level"]').value;

    // Tạo URL với các tham số tìm kiếm trên thanh lọc
    const params = new URLSearchParams({
        page: page,
        courseTitle: courseTitle,
        dateFrom: dateFrom,
        isPublic: isPublic,
        level: level
    })

    fetch(`api/admin/courses?${params.toString()}`)
        .then(res => {
            if (!res.ok) throw new Error("Network response was not ok");
            return res.json();
        })
        .then(data => {
            // Cái Data này được gửi từ Servlet (API) và eép theo dạng PageResponse
            renderAdminTable(data.data)
            renderPagination(data.currentPage, data.totalPage, data.totalElement, 'loadAdminCourses')
        })
        .catch(err => console.error("Lỗi khi tải dữ liệu:", err));
}

document.addEventListener('DOMContentLoaded', function () {
    // Gọi lần đầu khi load trang
    loadAdminCourses(1);

    // Gán sự kiện cho nút search
    const searchBtn = document.querySelector('.admin-search-btn');
    if (searchBtn) {
        searchBtn.onclick = function (e) {
            e.preventDefault();
            loadAdminCourses(1);
        };
    }
});

function renderAdminTable(courses) {
    const tbody = document.getElementById('admin-course-table-body')
    const template = document.getElementById('course-row-template')

    tbody.innerHTML = '';

    //Xử lý hiển thị rỗng nếu không có bất kỳ thằng nào
    if (!courses || courses.length === 0) {
        const tplEmpty = document.getElementById('tpl-empty-state');
        const emptyRow = tplEmpty.content.cloneNode(true);
        tbody.appendChild(emptyRow);
        return;
    }

    courses.forEach(course => {
        const clone = template.content.cloneNode(true);

        clone.querySelector('.js-title').innerText = course.title;
        clone.querySelector('.js-duration').innerText = course.durationText;
        clone.querySelector('.js-enrollment').innerText = course.studentCount;

        const level = clone.querySelector('.js-level');
        if(course.level === 'BEGINNER'){
            level.innerText = 'sơ cấp';
        } else if(course.level === 'INTERMEDIATE'){
            level.innerText = 'trung cấp';
        } else if (course.level === 'ADVANCED') {
            level.innerText = 'cao cấp';
        } else {
            level.innerText = 'không xác định'
        }

        // Xử lý status (Add class và text)l
        const statusDiv = clone.querySelector('.js-status');
        statusDiv.innerText = course.isPublic ? 'Công khai' : 'Riêng tư';
        statusDiv.classList.add(course.isPublic ? 'course-row__status-public' : 'course-row__status-private');
        clone.querySelector('.js-created').innerText = formatDate(course.createdAt);

        clone.querySelector('.js-edit-link').href = `admin/course/detail?id=${course.id}`;

        // Gán sự kiện xóa
        clone.querySelector('.js-delete-btn').onclick = () => openConfirmModal(course.id);

        // 3. Chèn hàng vừa tạo vào bảng
        tbody.appendChild(clone);

    })
}

// Lắng nghe sự kiện bấm nút Search để không load lại trang
document.querySelector('.admin-search-btn').addEventListener('click', function (e) {
    e.preventDefault(); // Chặn load lại trang
    loadAdminCourses(1); // Quay về trang 1 khi bấm tìm kiếm
});

// Chạy lần đầu khi load trang
document.addEventListener('DOMContentLoaded', () => loadAdminCourses(1));

