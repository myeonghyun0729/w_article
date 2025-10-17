package com.w.article.service;

import com.w.article.entity.Article;
import com.w.article.entity.Favorite;
import com.w.article.repository.ArticleRepository;
import com.w.article.repository.FavoriteRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    private final ArticleRepository articleRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, ArticleRepository articleRepository) {
        this.favoriteRepository = favoriteRepository;
        this.articleRepository = articleRepository;
    }

    @Transactional
    public Page<Favorite> selectFavoriteList(String keyword, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("createdAt").descending());
        if (StringUtils.isBlank(keyword)) {
            return favoriteRepository.findAll(pageable);
        }
        return favoriteRepository.findByArticleSubject(keyword, pageable);
    }

    @Transactional
    public boolean checkFavoriteByArticleIdx(Integer articleId) {
        return favoriteRepository.existsByArticleIdx(articleId);
    }

    @Transactional
    public Favorite createFavorite(Integer articleIdx) {
        if (favoriteRepository.existsByArticleIdx(articleIdx)) {
            return null;
        }

        Article article = articleRepository.getReferenceById(articleIdx);

        Favorite favorite = Favorite.builder()
                .article(article)
                .build();

        return favoriteRepository.save(favorite);
    }

    @Transactional
    public void deleteFavorite(Integer favoriteId) {
        Favorite favorite = favoriteRepository.findById(favoriteId)
                .orElseThrow(() -> new IllegalArgumentException("Favorite not found"));

        favoriteRepository.delete(favorite);
    }
}

