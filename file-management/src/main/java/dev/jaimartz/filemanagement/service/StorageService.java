package dev.jaimartz.filemanagement.service;

import dev.jaimartz.filemanagement.entity.ImageData;
import dev.jaimartz.filemanagement.repository.StorageRepository;
import dev.jaimartz.filemanagement.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {
    @Autowired
    private StorageRepository repository;

    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .build()
        );

        if(imageData != null) {
            return "file uploaded succesfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        if (!dbImageData.isEmpty()) {
            return ImageUtils.decompressImage(dbImageData.get().getImageData());
        }
        return null;
    }
}
