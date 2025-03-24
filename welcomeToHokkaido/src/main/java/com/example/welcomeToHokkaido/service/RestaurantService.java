package com.example.welcomeToHokkaido.service;

import com.example.welcomeToHokkaido.domain.dto.RestaurantDTO;
import com.example.welcomeToHokkaido.domain.entity.RestaurantEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.welcomeToHokkaido.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

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
                    .restaurantRating(entity.getRestaurantRating())
                    .restaurantDate(entity.getRestaurantDate())
                    .memberId(entity.getMemberId().getMemberId())
                    .build();
            dtoList.add(dto);
        }
        return dtoList;
    }
}
