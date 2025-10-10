package com.w.article.dto;

import com.w.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {

    private String cafeId;
    private String articleId;
    private String subject;
    private String summary;
    private String detailUrl;
    private Timestamp writeDateTimestamp;

    public static ArticleDto fromEntity(Article article) {
        return new ArticleDto(article.getCafeId(), article.getArticleId(), article.getSubject(),
                article.getSummary(), article.getDetailUrl(), article.getWriteDateTimestamp());
    }
}
