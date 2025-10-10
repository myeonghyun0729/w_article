package com.w.article.dto;

import com.w.article.entity.Cafe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CafeDto {

    private String cafeName;
    private String cafeId;
    private String menuId;

    public static CafeDto fromEntity(Cafe cafe) {
        return new CafeDto(cafe.getCafeName(), cafe.getCafeId(), cafe.getMenuId());
    }
}
