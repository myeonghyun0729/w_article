package com.w.article.controller;

import com.w.article.entity.Favorite;
import com.w.article.service.FavoriteService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/favorite")
public class FavoriteApiController {

    private final FavoriteService favoriteService;

    public FavoriteApiController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("/create")
    public Map<String, Object> createFavorite(@RequestParam Integer articleIdx) {
        if (articleIdx == null || articleIdx <= 0) {
            return Map.of("result", false, "message", "articleId not exist");
        }

        if (favoriteService.checkFavoriteByArticleIdx(articleIdx)) {
            return Map.of("result", false, "message", "already exist");
        }

        Favorite favorite = favoriteService.createFavorite(articleIdx);

        if (favorite == null) {
            return Map.of("result", false, "message", "insert fail");
        }
        return Map.of("result", true, "message", "insert success");
    }

    @PostMapping("/delete")
    public Map<String, Object> deleteFavorite(@RequestParam Integer favoriteId) {
        if (favoriteId == null || favoriteId <= 0) {
            return Map.of("result", false, "message", "favoriteId not exist");
        }
        favoriteService.deleteFavorite(favoriteId);

        return Map.of("result", true, "message", "delete success");
    }
}
