document.addEventListener('DOMContentLoaded', function () {
    setupAutoFilter({
        formId:'filterForm',
        tableBodyId: 'requestTableBody',
        url:'admin/requests'
    })
});