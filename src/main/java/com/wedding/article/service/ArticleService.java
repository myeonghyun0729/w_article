package com.wedding.article.service;

import com.wedding.article.entity.Article;
import com.wedding.article.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public Article createArticle() {
        Article article = new Article(
                15161515,
                "Spring JPA Example",
                "JPA insert 예제입니다.",
                Timestamp.from(Instant.now()),
                "https://example.com/detail/1"
        );
        return articleRepository.save(article); // INSERT 실행
    }
}

