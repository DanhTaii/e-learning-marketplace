const VideoHelper = {
    formatVideoUrl: function (url) {
        if (!url || url === "null" || url.trim() === "") return null;

        url = url.trim();

        //Xử lý link của Youtube
        if (url.includes("youtube.com") || url.includes("youtu.be")) {
            let videoId = "";
            if (url.includes("v=")) {
                videoId = url.split('v=')[1].split('&')[0];
            } else if (url.includes("youtu.be/")) {
                videoId = url.split('youtu.be/')[1].split('?')[0];
            }
            return videoId ? `https://www.youtube.com/embed/${videoId}` : null;
        }

        //Xử lý link của Cloudinary
        if (url.match(/\.(mp4|mkv|webm|ogg)$/) || url.includes("cloudinary.com")) {
            return url;
        }

        return url;
    },

    //Kiểm tra xem có phải là link youtuber hay không (link nhúng)
    isEmbedSource: function (url) {
        if (!url) return false;
        return url && (url.includes("youtube.com") || url.includes("youtu.be"));
    },

}