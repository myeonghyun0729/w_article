package com.w.article.entity;

import com.w.article.dto.CafeDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "cafe")
public class Cafe extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(length = 20)
    private String cafeName;

    @Column(length = 20)
    private String cafeId;

    @Column(length = 20)
    private String menuId;

    public static Cafe fromDto(CafeDto dto) {
        return Cafe.builder().cafeName(dto.getCafeName())
                .cafeId(dto.getCafeId())
                .menuId(dto.getMenuId())
                .build();
    }
}
