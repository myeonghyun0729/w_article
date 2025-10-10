package com.w.article.controller;

import com.w.article.dto.ArticleDto;
import com.w.article.entity.Article;
import com.w.article.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/list")
    public List<Article> getArticleList() {
        return articleService.selectArticleList();
    }

    @PostMapping("/create")
    public Article createArticle(ArticleDto dto) {
        return articleService.createArticle(Article.fromDto(dto));
    }
}

