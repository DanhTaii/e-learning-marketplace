// này là để lọc danh mục
document.addEventListener('DOMContentLoaded', function () {
    setupAutoFilter({
        formId:'filterForm',
        tableBodyId: 'categoryTableBody',
        url:'admin/categories'
    })
});
