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

    @OneToMany(mappedBy = "restaurantId", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ImageEntity> imageEntities = new ArrayList<>();
}
