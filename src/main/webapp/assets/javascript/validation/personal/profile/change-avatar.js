document.addEventListener("DOMContentLoaded", () => {

    const avatarPreview = document.getElementById("avatarPreview");
    const avatarInput = document.getElementById("avatarInput");
    const avatarForm = document.getElementById("avatarForm");

    if (!avatarPreview || !avatarInput || !avatarForm) return;

    // Click avatar
    avatarPreview.addEventListener("click", () => {
        avatarInput.click();
    });

    // Chọn ảnh
    avatarInput.addEventListener("change", function () {

        const file = this.files[0];

        if (file) {

            // Preview
            avatarPreview.src = URL.createObjectURL(file);

            // Auto upload
            avatarForm.submit();
        }
    });
});