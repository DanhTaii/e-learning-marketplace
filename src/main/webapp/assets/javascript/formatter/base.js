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