package com.w.article.repository;

import com.w.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    boolean existsByCafeIdAndArticleId(String cafeId, String articleId);
}
