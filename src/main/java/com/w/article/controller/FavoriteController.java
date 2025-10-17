package com.w.article.controller;

import com.w.article.entity.Favorite;
import com.w.article.service.FavoriteService;
import com.w.article.utils.RestApiClient;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;

    private final Integer DEFAULT_PAGE_SIZE = 15;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping("/list")
    public String articleList(Model model, @RequestParam(required = false) String keyword,
                                 @RequestParam(required = false, defaultValue = "0") Integer page) {
        Page<Favorite> favoriteList = favoriteService.selectFavoriteList(keyword, page, DEFAULT_PAGE_SIZE);

        model.addAttribute("favoriteList", favoriteList);
        return "favorite-list";
    }
}

