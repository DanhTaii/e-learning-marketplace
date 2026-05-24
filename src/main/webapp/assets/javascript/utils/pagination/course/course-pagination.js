function loadAdminCourses(page = 1) {
    const params = buildFilterParams(page);

    fetch(`api/admin/courses?${params}`, {
        headers: {
            'X-CSRF-Token': getCsrfToken()
        }
    })
        .then(res => {
            if (!res.ok) throw new Error("Network response was not ok");
            return res.json();
        })
        .then(data => {
            renderAdminTable(data.data);

            // ✅ FIX QUAN TRỌNG: truyền object + function
            renderPagination(data, loadAdminCourses);
        })
        .catch(err => console.error("Lỗi khi tải dữ liệu:", err));
}

function buildFilterParams(page) {
    const courseTitle = document.querySelector('input[name="courseTitle"]')?.value || '';
    const dateFrom = document.querySelector('input[name="dateFrom"]')?.value || '';
    const isPublic = document.querySelector('select[name="isPublic"]')?.value || '';
    const level = document.querySelector('select[name="level"]')?.value || '';

    const params = new URLSearchParams({
        page: page,
        courseTitle,
        dateFrom,
        isPublic,
        level
    });

    return params.toString();
}

function renderAdminTable(courses) {
    const tbody = document.getElementById('admin-course-table-body');
    const template = document.getElementById('course-row-template');

    tbody.innerHTML = '';

    if (!courses || courses.length === 0) {
        const tplEmpty = document.getElementById('tpl-empty-state');
        tbody.appendChild(tplEmpty.content.cloneNode(true));
        return;
    }

    courses.forEach(course => {
        const clone = template.content.cloneNode(true);

        clone.querySelector('.js-title').innerText = course.title;
        clone.querySelector('.js-duration').innerText = course.durationText;
        clone.querySelector('.js-enrollment').innerText = course.studentCount;

        // Level mapping (clean hơn)
        const levelMap = {
            BEGINNER: 'sơ cấp',
            INTERMEDIATE: 'trung cấp',
            ADVANCED: 'cao cấp'
        };

        clone.querySelector('.js-level').innerText =
            levelMap[course.level] || 'không xác định';

        // Status
        const statusDiv = clone.querySelector('.js-status');
        statusDiv.innerText = course.isPublic ? 'Công khai' : 'Riêng tư';
        statusDiv.classList.add(
            course.isPublic
                ? 'course-row__status-public'
                : 'course-row__status-private'
        );

        clone.querySelector('.js-created').innerText = formatDate(course.createdAt);
        clone.querySelector('.js-edit-link').href = `admin/course/detail?id=${course.id}`;
        clone.querySelector('.js-delete-btn').onclick = () => openConfirmModal(course.id);

        tbody.appendChild(clone);
    });
}

document.addEventListener('DOMContentLoaded', () => {
    // load lần đầu
    loadAdminCourses(1);

    // search
    const searchBtn = document.querySelector('.admin-search-btn');
    if (searchBtn) {
        searchBtn.onclick = (e) => {
            e.preventDefault();
            loadAdminCourses(1);
        };
    }
});