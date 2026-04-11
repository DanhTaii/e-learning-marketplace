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
            $('#videoLocalPlayer').attr('src', blobURL).show();
            $('#videoIframe').hide();
            $('#videoPreviewContainer').slideDown();
        }
    });
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