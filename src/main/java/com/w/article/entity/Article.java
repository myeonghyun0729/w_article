package com.w.article.entity;

import com.w.article.dto.ArticleDto;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "article")
public class Article extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;
    private String cafeId;
    private String articleId;
    private String subject;
    private String summary;
    private String detailUrl;
    private Timestamp writeDateTimestamp;

    public static Article fromDto(ArticleDto dto) {
        return Article.builder().cafeId(dto.getCafeId())
                .articleId(dto.getArticleId())
                .subject(dto.getSubject())
                .summary(dto.getSummary())
                .detailUrl(dto.getDetailUrl())
                .writeDateTimestamp(dto.getWriteDateTimestamp())
                .build();
    }
}
