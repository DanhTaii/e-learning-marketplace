document.addEventListener('DOMContentLoaded', function () {
    setupAutoFilter({
        formId: 'filterForm',
        tableBodyId: 'courseTableBody',
        url: 'admin/courses'
    });
});