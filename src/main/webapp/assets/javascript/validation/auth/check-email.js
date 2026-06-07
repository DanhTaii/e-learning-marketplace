const inputs = document.querySelectorAll('.otp-inputs input');
const fullOtp = document.getElementById('fullOtp');

inputs.forEach((input, index) => {
    input.addEventListener('input', function () {
        if (this.value.length === 1) {
            if (index < inputs.length - 1) {
                inputs[index + 1].focus();
            }
            updateFullOtp();
        }
    });

    input.addEventListener('keydown', function (e) {
        if (e.key === "Backspace" && this.value === "" && index > 0) {
            e.preventDefault();
            inputs[index - 1].focus();
        }
    });

    input.addEventListener('paste', function (e) {
        e.preventDefault();
        const pastedData = e.clipboardData.getData('text').trim();
        if (pastedData.length === 5 && /^[A-Z0-9]{5}$/.test(pastedData)) {
            for (let i = 0; i < 5; i++) {
                inputs[i].value = pastedData[i];
            }
            updateFullOtp();
            inputs[4].focus();
        }
    });
});

function updateFullOtp() {
    let code = '';
    inputs.forEach(input => {
        code += input.value;
    });
    fullOtp.value = code;
}

document.getElementById('otpForm').addEventListener('submit', function (e) {
    if (fullOtp.value.length !== 5) {
        e.preventDefault();
        alert('Vui lòng nhập đủ 5 chữ số!');
    }
});