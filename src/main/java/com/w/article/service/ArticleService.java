package com.w.article.service;

import com.w.article.entity.Article;
import com.w.article.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public List<Article> selectArticleList() {

        boolean result = articleRepository.existsByCafeIdAndArticleId("1", "15161515");
        System.out.println(result);
        return articleRepository.findAll();
    }

    @Transactional
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }
}

