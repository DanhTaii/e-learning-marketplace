function toast({title = '', message = '', type = 'info', duration = 3000}) {
    console.log("Toast Type:", type);
    const main = document.getElementById('toast');
    if (main) {
        const icons = {
            success: 'fa-solid fa-circle-check',
            info: 'fa-solid fa-circle-info',
            warning: 'fa-solid fa-circle-exclamation',
            error: 'fa-solid fa-circle-xmark'
        };

        const icon = icons[type];

        const toast = document.createElement('div');

        const autoRemoveId = setTimeout(function () {
            main.removeChild(toast);
        }, duration + 1000);

        toast.onclick = function (e) {
            if (e.target.closest('.toast__close')) {
                main.removeChild(toast);
                clearTimeout(autoRemoveId);
            }
        };
        const delay = (duration / 1000).toFixed(2);

        toast.classList.add('toast');
        toast.classList.add('toast--' + type);
        toast.style.animation = `slideInLeft ease 0.3s, fadeOut linear 1s ${delay}s forwards`;

        toast.innerHTML = `
                <div class="toast__icon"><i class="${icon}"></i></div>
                <div class="toast__body">
                    <h3 class="toast__title">` + title + `</h3>
                    <p class="toast__msg">` + message + `</p>
                </div>
                <div class="toast__close"><i class="fa-solid fa-xmark"></i></div>
            `;
        main.appendChild(toast);
    }
}

window.addEventListener('load', function () {
    const errorMsg = String(window.flashError || "").trim();
    const successMsg = String(window.flashSuccess || "").trim();


    if (successMsg !== "" && successMsg !== "null") {
        toast({title: 'Thành công!', message: successMsg, type: 'success', duration: 4000});
    }

    if (errorMsg !== "" && errorMsg !== "null") {
        toast({title: 'Thất bại!', message: errorMsg, type: 'error', duration: 6000});
    }
});