package com.example.EStore.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.UUID;

@Service
public class ImageCloudService {

    private Cloudinary cloudinary;

    public ImageCloudService() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dameipk4b",
                "api_key", "184963443187464",
                "api_secret", "dvkoyH4GewQWf3qczNPrgem_uF0",
                "secure", true
        ));
    }

    public String saveImage(MultipartFile multipartFile) {
        String imageId = UUID.randomUUID().toString();

        Map params = ObjectUtils.asMap(
                "public_id", imageId,
                "overwrite", true,
                "resource_type", "image"
        );

        File tmpFile = new File(imageId);
        try {
            Files.write(tmpFile.toPath(), multipartFile.getBytes());
            cloudinary.uploader().upload(tmpFile, params);
            Files.delete(tmpFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return String.format("https://res.cloudinary.com/dameipk4b/image/upload/v1678739665/" +
                imageId + "." + getFileExtension(multipartFile.getOriginalFilename()));
    }

    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf('.') + 1);
    }
}
