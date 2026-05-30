// này là để lọc danh mục
document.addEventListener('DOMContentLoaded', function () {
    setupAutoFilter({
        formId:'filterForm',
        tableBodyId: 'paymentMethodTableBody',
        url:'admin/payment-methods'
    })
});
