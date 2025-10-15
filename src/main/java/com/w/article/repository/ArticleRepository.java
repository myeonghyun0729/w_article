package com.w.article.repository;

import com.w.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    boolean existsByCafeIdAndArticleId(String cafeId, String articleId);

    Page<Article> findListBySubjectContainingIgnoreCase(String keyword, Pageable pageable);
}
