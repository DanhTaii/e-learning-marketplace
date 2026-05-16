function formatDate(dateString) {
    if (!dateString) return "";

    // JS có thể đọc được định dạng ngày từ JSON trả về
    const date = new Date(dateString);

    // Dùng Intl.DateTimeFormat (Chuẩn hiện đại)
    return new Intl.DateTimeFormat('vi-VN', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric'
    }).format(date).replace(/\//g, '-'); // Biến / thành -
}

function formatTime(totalSeconds) {
    // Chia 60 để lấy phuút và làm tròn xuống
    const minutes = Math.floor(totalSeconds / 60)
        .toString()
        .padStart(2, '0');

    // Chia 60 laays phần dư
    const seconds = (totalSeconds % 60)
        .toString()
        .padStart(2, '0');
    // Ví dụ dưới DB là 320s thì:
    // minute = 320 / 60 = 5.333 => với floor = 5'
    // seconds = 320 % 60 = 20 => 20s
    return `${minutes} : ${seconds}`;
}