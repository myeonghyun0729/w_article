package com.w.article.controller;

import com.w.article.entity.Article;
import com.w.article.service.ArticleService;
import com.w.article.utils.RestApiClient;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    private final Integer DEFAULT_PAGE_SIZE = 15;

    public ArticleController(ArticleService articleService, RestApiClient restApiClient) {
        this.articleService = articleService;
    }

    @GetMapping("/list")
    public String articleList(Model model, @RequestParam(required = false) String keyword,
                                 @RequestParam(required = false, defaultValue = "0") Integer page) {
        Page<Article> articleList = articleService.selectArticleList(keyword, page, DEFAULT_PAGE_SIZE);

        model.addAttribute("articleList", articleList);
        return "article-list";
    }
}

