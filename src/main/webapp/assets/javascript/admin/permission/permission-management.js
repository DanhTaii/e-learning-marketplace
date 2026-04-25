document.addEventListener('DOMContentLoaded', function () {
    setupAutoFilter({
        formId:'filterForm',
        tableBodyId: 'permissionTableBody',
        url:'admin/super/permissions'
    })
});