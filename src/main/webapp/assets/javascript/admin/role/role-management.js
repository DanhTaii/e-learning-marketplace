document.addEventListener('DOMContentLoaded', function () {
    setupAutoFilter({
        formId:'filterForm',
        tableBodyId: 'roleTableBody',
        url:'admin/super/roles'
    })
});