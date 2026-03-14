    document.addEventListener('DOMContentLoaded', function() {

    const payBtn = document.getElementById('pay-btn');
    const paymentForm = document.querySelector('.payment-layout');
    const confirmPaymentBtn = document.getElementById('btn-confirm-payment');

    if (payBtn) {
    payBtn.onclick = function(e) {
    e.preventDefault();
    openModal('popup__add-payment-confirm-black');
};
}

    if (confirmPaymentBtn) {
    confirmPaymentBtn.onclick = function() {
    if (paymentForm) {
    paymentForm.submit();
}
};
}
});


    const originalWindowClick = window.onclick;
    window.onclick = function (event) {
    if (originalWindowClick) originalWindowClick(event);

    if (event.target.id === 'popup__add-payment-confirm-black') {
    closeModal('popup__add-payment-confirm-black');
}
};
