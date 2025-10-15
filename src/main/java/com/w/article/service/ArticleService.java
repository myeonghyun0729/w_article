package com.w.article.service;

import com.w.article.entity.Article;
import com.w.article.repository.ArticleRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public Page<Article> selectArticleList(String keyword, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("writeDateTimestamp").descending());
        if (StringUtils.isBlank(keyword)) {
            return articleRepository.findAll(pageable);
        }
        return articleRepository.findListBySubjectContainingIgnoreCase(keyword, pageable);
    }

    @Transactional
    public boolean existsByCafeIdAndArticleId(String cafeId, String articleId) {
        return articleRepository.existsByCafeIdAndArticleId(cafeId, articleId);
    }

    @Transactional
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }
}

