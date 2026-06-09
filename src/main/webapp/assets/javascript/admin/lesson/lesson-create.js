function previewVideoSource() {
    //Lấy đường dẫn URL (này có cả 2 loại là cloudinary và youtuber)
    const url = $('#videoUrlInput').val().trim();
    const urlFinal = VideoHelper.formatVideoUrl(url);

    //Tag hiển thị cho Youtube
    const iframe = $('#videoIframe');

    //Tag hiển thị cho Cloudinary
    const localPlayer = $('#videoLocalPlayer');

    //Khung chứa cả 2 loại thẻ video (iframe và video)
    const container = $('#videoPreviewContainer');

    if (!urlFinal) {
        $('#videoPreviewContainer').slideUp();
        alert("Đường dẫn video không hợp lệ hoặc không hỗ trợ xem trước!");
        return;
    }

    //Kiểm tra nếu là link nhúng (Youtube) thì hiển thị iframe
    //Nếu không phải thì là link cloudinary
    if (VideoHelper.isEmbedSource(urlFinal)) {
        iframe.attr('src', urlFinal);
        iframe.show();
        localPlayer.hide();
        container.slideDown();
    } else {
        localPlayer.attr('src', urlFinal);
        localPlayer.show();
        iframe.hide();
        container.slideDown();
    }

}

//Tự động load preview nếu đã có sẵn hoặc là lúc vừa upload/gán link vào
$(document).ready(function () {
    // Nếu nó có sẵn thì load lên
    if ($('#videoUrlInput').val()) {
        previewVideoSource();
    }

    //Load preview khi có người nhập đường link vào input
    $('#videoUrlInput').on('input', function () {
        previewVideoSource();
    });

    // Load video khi upload video lên
    $('#videoFileInput').on('change', function (event) {
        const file = event.target.files[0];
        if (file) {
            //Tạo ra 1 URL giả hay tạm thời để có thể preview
            const blobURL = URL.createObjectURL(file);
            const localPlayer = $('#videoLocalPlayer');

            localPlayer.attr('src', blobURL).show();
            $('#videoIframe').hide();
            $('#videoPreviewContainer').slideDown()

            // Báo cho user biết đang tính toán thời gian
            $('#durationMinutes').attr('placeholder', 'Đang tính toán...');

            // LOGIC LẤY THỜI GIAN: Chờ video load xong metadata
            localPlayer[0].onloadedmetadata = function () {
                // this.duration trả về thời gian video tính bằng GIÂY (seconds)
                const durationInSeconds = this.duration;

                // Đổi ra phút và làm tròn (VD: 1.2 phút -> 1 phút, 1.6 phút -> 2 phút)
                let durationInMinutes = Math.round(durationInSeconds / 60);

                // Nếu video quá ngắn (dưới 30 giây), làm tròn sẽ ra 0. Ta set mặc định tối thiểu là 1 phút.
                if (durationInMinutes < 1) {
                    durationInMinutes = 1;
                }

                // Đổ dữ liệu vào ô input "Thời lượng (Phút)"
                $('#durationMinutes').val(durationInMinutes);

                // Trả lại placeholder cũ nếu cần
                $('#durationMinutes').attr('placeholder', 'Phút');

                // Xóa câu thông báo lỗi cũ (nếu có) để giao diện sạch đẹp
                $('#error_durationMinutes').text('');

            };
        }
    });

    //Ẩn đi nút submit sau khi người dùng nhấn vào để tránh việc họ bấm nhiều lần
    $('#lessonForm').on('submit', function (event) {
        // Lấy ra nút submit bên trong form này
        const submitBtn = $(this).find('.btn-submit-modern');

        submitBtn.prop('disabled', true).text('Đang lưu...');

        submitBtn.css({
            'opacity': '0.7',
            'cursor': 'not-allowed'
        });

    })
});

function switchVideoSource(type, el) {
    $('.tab-btn').removeClass('active');
    $(el).addClass('active');

    if (type === 'link') {
        $('#videoSourceLink').fadeIn(300);
        $('#videoSourceUpload').hide();
    } else {
        $('#videoSourceUpload').fadeIn(300);
        $('#videoSourceLink').hide();
    }
}