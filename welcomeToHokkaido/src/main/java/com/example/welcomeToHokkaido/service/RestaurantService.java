package com.example.welcomeToHokkaido.service;

import com.example.welcomeToHokkaido.domain.dto.RestaurantDTO;
import com.example.welcomeToHokkaido.domain.entity.ImageEntity;
import com.example.welcomeToHokkaido.domain.entity.RestaurantEntity;
import com.example.welcomeToHokkaido.repository.ImageRepository;
import com.example.welcomeToHokkaido.repository.MemberRepository;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.welcomeToHokkaido.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ImageRepository imageRepository;
    private final ImageService imageService;

    public List<RestaurantDTO> getList() {

        List<RestaurantEntity> entities = restaurantRepository.findAll();

        List<RestaurantDTO> dtoList = new ArrayList<>();

        for (RestaurantEntity entity : entities) {
            RestaurantDTO dto = RestaurantDTO.builder()
                    .restaurantId(entity.getRestaurantId())
                    .restaurantTitle(entity.getRestaurantTitle())
                    .restaurantContent(entity.getRestaurantContent())
                    .restaurantRating(entity.getRestaurantRating())
                    .restaurantImage(entity.getRestaurantImage())
                    .restaurantView(entity.getRestaurantView())
                    .restaurantDate(entity.getRestaurantDate())
//                    .memberId(entity.getMemberId().getMemberId())
                    .build();
            dtoList.add(dto);
        }
        return dtoList;
    }

    public Integer write(RestaurantDTO restaurantDTO) {
//        MemberEntity memberEntity = memberRepository.findById(restaurantDTO.getMemberId())
//                .orElseThrow(() -> new EntityNotFoundException("회원 정보가 없습니다."));
        RestaurantEntity restaurantEntity = RestaurantEntity.builder()
//                .memberId(memberEntity)
                .restaurantTitle(restaurantDTO.getRestaurantTitle())
                .restaurantContent(restaurantDTO.getRestaurantContent())
                .restaurantView(0)
                .restaurantRating(restaurantDTO.getRestaurantRating())
                .build();

        restaurantRepository.save(restaurantEntity);

        return restaurantEntity.getRestaurantId();
    }

    public RestaurantDTO read(int restaurantId) {
        RestaurantEntity entity = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("글이 없습니다."));

        List<String> imagePaths = entity.getImageEntities().stream()
                .map(imageEntity -> imageEntity.getImagePath().replace("C:", ""))
                .collect(Collectors.toList());

        System.out.println("이미지나오나요" + imagePaths);

        RestaurantDTO restaurantDTO = RestaurantDTO.builder()
                .restaurantId((entity.getRestaurantId()))
                .restaurantTitle(entity.getRestaurantTitle())
                .restaurantContent(entity.getRestaurantContent())
                .restaurantRating(entity.getRestaurantRating())
                .restaurantImage(entity.getRestaurantImage())
                .restaurantView(entity.getRestaurantView())
                .restaurantDate(entity.getRestaurantDate())
                .imagePath(imagePaths)
                .build();

        return restaurantDTO;
    }

    public void delete(int restaurantId) {
        RestaurantEntity restaurantEntity = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("게시물이 없습니다."));
        restaurantRepository.delete(restaurantEntity);
    }

    public void update(RestaurantDTO restaurantDTO) {
        RestaurantEntity restaurantEntity = restaurantRepository.findById(restaurantDTO.getRestaurantId())
                .orElseThrow(() -> new EntityNotFoundException("게시물을 찾을 수 없습니다."));
        restaurantEntity.setRestaurantTitle(restaurantDTO.getRestaurantTitle());
        restaurantEntity.setRestaurantContent(restaurantDTO.getRestaurantContent());
        restaurantEntity.setRestaurantRating(restaurantDTO.getRestaurantRating());
        //나중에 이미지도 추가
        restaurantRepository.save(restaurantEntity);
    }
}
