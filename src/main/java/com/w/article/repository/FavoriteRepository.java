package com.w.article.repository;

import com.w.article.entity.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    Page<Favorite> findByArticleSubject(String keyword, Pageable pageable);

    boolean existsByArticleIdx(Integer articleIdx);
}
