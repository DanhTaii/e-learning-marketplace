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
    },
    delete_note: {
        title: 'xóa ghi chú',
        message: 'Ghi chú sẽ bị xóa vĩnh viễn!',
        btnText: 'Xóa ghi chú',

        btnClass: 'btn-danger',
        titleClass: 'title-danger',
        icon: 'fa-trash',
        showReason: false
    },
    revoke_cert: {
        title: 'thu hồi chứng chỉ',
        message: 'Chứng chỉ sẽ bị thu hồi!',
        btnText: 'Thu hồi',

        btnClass: 'btn-danger',
        titleClass: 'title-danger',
        icon: 'fa-lock',
        showReason: false
    },
    reinstate_cert: {
        title: 'cấp lại chứng chỉ',
        message: 'Chứng chỉ sẽ được cấp lại!',
        btnText: 'Cấp lại',

        btnClass: 'btn-primary',
        titleClass: 'title-primary',
        icon: 'fa-lock-open',
        showReason: false
    }
};