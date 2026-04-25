const MODAL_CONFIGS = {
    archive: {
        title: 'lưu trữ',
        message: 'Bạn có chắc muốn đưa mục này vào kho lưu trữ?',
        btnText: 'Lưu trữ ngay',

        btnClass: 'btn-danger',
        titleClass: 'title-danger',
        icon: 'fa-box-archive',
        showReason: true
    },
    delete: {
        title: 'xóa vĩnh viễn',
        message: 'Hành động này không thể hoàn tác !',
        btnText: 'Xóa vĩnh viễn',

        btnClass: 'btn-danger',
        titleClass: 'title-danger',
        icon: 'fa-trash',
        showReason: false
    },
    duplicate: {
        title: 'nhân bản',
        message: 'Hệ thống sẽ tạo một bản sao tương tự mục này.',
        btnText: 'Nhân bản ngay',

        btnClass: 'btn-primary',
        titleClass: 'title-primary',
        icon: 'fa-copy',
        showReason: false
    },
    update_status: {
        title: 'cập nhật trạng thái',
        message: 'Hệ thống sẽ đổi trạng thái của mục này',
        btnText: 'Cập nhật ngay',

        btnClass: 'btn-dark',
        titleClass: 'title-dark',
        icon: 'fa-pen',
        showReason: false
    },
    restore: {
        title: 'khôi phục',
        message: 'Hệ thống sẽ khôi phục mục này',
        btnText: 'Khôi phục ngay',

        btnClass: 'btn-primary',
        titleClass: 'title-primary',
        icon: 'fa-rotate-left',
        showReason: false
    }
};