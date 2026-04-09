function switchVideoSource(type, el) {
    $('.tab-btn').removeClass('active');
    $(el).addClass('active'); // ✅ luôn đúng

    if (type === 'link') {
        $('#videoSourceLink').fadeIn(300);
        $('#videoSourceUpload').hide();
    } else {
        $('#videoSourceUpload').fadeIn(300);
        $('#videoSourceLink').hide();
    }
}