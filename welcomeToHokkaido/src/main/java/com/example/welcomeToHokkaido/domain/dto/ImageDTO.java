package com.example.welcomeToHokkaido.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
    int imageId;
    String imagePath;
    int restaurantId;
}
