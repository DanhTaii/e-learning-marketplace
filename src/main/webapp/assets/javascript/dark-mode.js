const themeBtn = document.getElementById('theme-toggle');
const icon = themeBtn.querySelector('i');

function setTheme(theme) {
    if (theme === 'dark') {
        document.body.classList.add('dark-mode'); // Thêm class vào body
        icon.classList.replace('fa-moon', 'fa-sun');
        localStorage.setItem('theme', 'dark');
    } else {
        document.body.classList.remove('dark-mode'); // Xóa class khỏi body
        icon.classList.replace('fa-sun', 'fa-moon');
        localStorage.setItem('theme', 'light');
    }
}

themeBtn.addEventListener('click', () => {
    const isDark = document.body.classList.contains('dark-mode');
    setTheme(isDark ? 'light' : 'dark');
});

window.addEventListener('DOMContentLoaded', () => {
    const savedTheme = localStorage.getItem('theme');

    if (savedTheme) {
        // Nếu đã có lựa chọn lưu trong máy, dùng lựa chọn đó
        setTheme(savedTheme);
    } else {
        // Nếu chưa có lựa chọn, kiểm tra cài đặt của Windows
        const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
        setTheme(prefersDark ? 'dark' : 'light');
    }
});