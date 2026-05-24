function getCsrfToken() {
    const metaTag = document.querySelector('meta[name="csrf-token"]');
    if (metaTag) {
        return metaTag.getAttribute('content');
    }
    console.error("Lỗi: Không tìm thấy thẻ meta csrf-token trên giao diện!");
    return '';
}