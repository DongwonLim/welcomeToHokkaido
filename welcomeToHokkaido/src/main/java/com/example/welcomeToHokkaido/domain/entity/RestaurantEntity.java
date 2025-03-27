package com.example.welcomeToHokkaido.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "restaurant")
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id", nullable = false)
    private Integer restaurantId;

    @Column(name = "restaurant_title", nullable = false)
    private String restaurantTitle;

    @Column(name = "restaurant_content", nullable = false)
    private String restaurantContent;

    @Column(name = "restaurant_rating", nullable = false)
    private Integer restaurantRating;

    @CreatedDate
    @Column(name = "restaurant_date", columnDefinition = "timestamp default current_timestamp on update current_timestamp", insertable = false, updatable = false)
    private LocalDateTime restaurantDate;

    @Column(name = "restaurant_image_url")
    private String restaurantImage;

    @Column(name = "restaurant_view")
    private int restaurantView;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    MemberEntity memberId;

    /* mappedBy - 연관 관계의 주인 지정, ImageEntity의 restaurantId 필드와 관계를 맺는다.
    ImageEntity클래스에서 @ManyToOne 에노테이션을 사용하여 restaurantId와 연결된다는 듯
    cascade = CascadeType.All - 영속성 전파 설정, restaurantId가 저장, 수정, 삭제 될때 image도 함께 처리
    orphanRemoval = true - 부모 엔티티에서 관계를 끊은 자식 엔티티를 자동으로 삭제, restaurantId에서
    image목록에 있는 한 항목을 삭제하면, 해당 항목은 DB에서 제거
    */
    @OneToMany(mappedBy = "restaurantId", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ImageEntity> imageEntities = new ArrayList<>();
}
