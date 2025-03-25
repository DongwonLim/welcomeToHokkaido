package com.example.welcomeToHokkaido.service;

import com.example.welcomeToHokkaido.domain.dto.RestaurantDTO;
import com.example.welcomeToHokkaido.domain.entity.MemberEntity;
import com.example.welcomeToHokkaido.domain.entity.RestaurantEntity;
import com.example.welcomeToHokkaido.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
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
    private final MemberRepository memberRepository;

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
        RestaurantDTO restaurantDTO = RestaurantDTO.builder()
                .restaurantTitle(entity.getRestaurantTitle())
                .restaurantContent(entity.getRestaurantContent())
                .restaurantRating(entity.getRestaurantRating())
                .restaurantImage(entity.getRestaurantImage())
                .restaurantView(entity.getRestaurantView())
                .restaurantDate(entity.getRestaurantDate())
                .build();

        return restaurantDTO;
    }
}
