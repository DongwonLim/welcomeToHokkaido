package com.example.welcomeToHokkaido.domain.dto;

import com.example.welcomeToHokkaido.domain.entity.ImageEntity;
import com.example.welcomeToHokkaido.domain.entity.RestaurantEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestaurantDTO {

    int restaurantId;
    String restaurantTitle;
    String restaurantContent;
    int restaurantRating;
    LocalDateTime restaurantDate;
    String restaurantImage;
    int restaurantView;
    String memberId;

}
