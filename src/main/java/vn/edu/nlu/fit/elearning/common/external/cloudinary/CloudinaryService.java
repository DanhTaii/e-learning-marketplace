package vn.edu.nlu.fit.elearning.common.external.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.Map;

public class CloudinaryService {

    private static Cloudinary cloudinary;

    // Singleton Pattern để khởi tạo Cloudinary một lần duy nhất
    public static Cloudinary getInstance() {
        if (cloudinary == null) {
            cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", System.getenv("CLOUDINARY_NAME"),
                    "api_key", System.getenv("CLOUDINARY_API_KEY"),
                    "api_secret", System.getenv("CLOUDINARY_API_SECRET"),
                    "secure", true
            ));
        }
        return cloudinary;
    }

    public static String uploadFile(Part filePart, String folderName) throws IOException {
        if (filePart == null || filePart.getSize() == 0) {
            throw new IllegalArgumentException("File không được để trống");
        }

        String contentType = filePart.getContentType();
        String resourceType = (contentType != null && contentType.startsWith("video/")) ? "video" : "image";

        java.nio.file.Path tempFile = java.nio.file.Files.createTempFile("upload-", "-" + filePart.getSubmittedFileName());

        try {
            filePart.write(tempFile.toString());

            Map uploadResult = getInstance().uploader().upload(
                    tempFile.toFile(),
                    ObjectUtils.asMap(
                            "folder", folderName,
                            "resource_type", resourceType
                    )
            );

            return (String) uploadResult.get("secure_url");
        } finally {
            java.nio.file.Files.deleteIfExists(tempFile);
        }
    }

    public static void deleteFile(String publicId) throws IOException {
        if (publicId != null && !publicId.isEmpty()) {
            getInstance().uploader().destroy(publicId, ObjectUtils.emptyMap());
        }
    }
}
