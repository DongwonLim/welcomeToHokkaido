package com.example.welcomeToHokkaido.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

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
    List<String> imagePath;

}
