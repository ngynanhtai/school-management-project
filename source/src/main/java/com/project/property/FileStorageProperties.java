package com.project.property;

import com.project.enums.MessageCodeEnum;
import com.project.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileStorageProperties {
    public static Path ROOT_UPLOAD_FOLDER;

    @Autowired
    public void loadFileStorageProperties(@Value("${storage.upload-folder}") String uploadFolder) {
        ROOT_UPLOAD_FOLDER = Paths.get(uploadFolder).toAbsolutePath().normalize();

        try {
            Files.createDirectories(ROOT_UPLOAD_FOLDER);
        } catch (Exception ex) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.STORAGE_INIT_ERROR, "Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
}
