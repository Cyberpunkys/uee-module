package ru.vstu.ueemodule.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileUploadingUtils {

    public static String uploadFile(MultipartFile file, String filename, String uploadPath) throws IOException {
        File uploadDir = new File(uploadPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String uuidPrefix = UUID.randomUUID().toString();
        String resultFilename = uuidPrefix + "." + filename;
        file.transferTo(new File(uploadPath + "/" + resultFilename));
        return resultFilename;
    }
}
