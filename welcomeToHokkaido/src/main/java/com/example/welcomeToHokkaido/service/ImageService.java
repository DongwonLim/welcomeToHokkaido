package com.example.welcomeToHokkaido.service;

import com.example.welcomeToHokkaido.domain.entity.ImageEntity;
import com.example.welcomeToHokkaido.domain.entity.RestaurantEntity;
import com.example.welcomeToHokkaido.repository.ImageRepository;
import com.example.welcomeToHokkaido.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
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
    private final RestaurantRepository restaurantRepository;
    private final String uploadDir = "C:/uploads/";

    //나중에 레스토랑 이미지업로드로 이름 변경해야될듯
    public void uploadImage(List<MultipartFile> images, int restaurantId) throws IOException {

        RestaurantEntity restaurantEntity = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("레스토랑을 찾을 수 없습니다."));

        for (MultipartFile image : images) {
            //중복방지
            String fileName = UUID.randomUUID() + "-" + image.getOriginalFilename();
            String filePath = uploadDir + fileName;

            //파일을 서버에 저장
            File destinationFile = new File(filePath);
            image.transferTo(destinationFile);

            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setImagePath(filePath);
            imageEntity.setRestaurantId(restaurantEntity);

            imageRepository.save(imageEntity);
        }
    }
}
