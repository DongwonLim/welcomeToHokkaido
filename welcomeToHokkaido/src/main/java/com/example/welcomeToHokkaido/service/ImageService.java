package com.example.welcomeToHokkaido.service;

import com.example.welcomeToHokkaido.domain.entity.ImageEntity;
import com.example.welcomeToHokkaido.repository.ImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final String uploadDir = "C:/uploads/";

    //왜 예외를 던지지?
    public void uploadImage(List<MultipartFile> images) throws IOException {
        for (MultipartFile image : images) {
            //중복방지
            String fileName = UUID.randomUUID() + "-" + image.getOriginalFilename();
            String filePath = uploadDir + fileName;

            //파일을 서버에 저장
            File destinationFile = new File(filePath);
            image.transferTo(destinationFile);

            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setImagePath(filePath);

            imageRepository.save(imageEntity);
        }


    }
}
