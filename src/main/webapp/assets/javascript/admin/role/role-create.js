document.addEventListener('DOMContentLoaded', function () {

    const form = document.querySelector('form');
    const nameInput = document.querySelector('input[name="name"]');
    const descInput = document.querySelector('textarea[name="description"]');

    const errorName = document.querySelector('#error_name');
    const errorDesc = document.querySelector('#error_description');

    form.addEventListener('submit', function (e) {

        let isValid = true;

        if (errorName) errorName.textContent = '';
        if (errorDesc) errorDesc.textContent = '';

        const name = nameInput.value.trim();

        if (name.length === 0) {
            errorName.textContent = 'Tên role không được để trống!';
            isValid = false;
        } else if (name.length < 3) {
            errorName.textContent = 'Tên role phải có ít nhất 3 ký tự!';
            isValid = false;
        } else if (name.length > 50) {
            errorName.textContent = 'Tên role không được vượt quá 50 ký tự!';
            isValid = false;
        }

        const desc = descInput.value.trim();

        if (desc.length > 255) {
            errorDesc.textContent = 'Mô tả không được vượt quá 255 ký tự!';
            isValid = false;
        }

        if (!isValid) {
            e.preventDefault();
        }
    });
});