package com.w.article.controller;

import com.w.article.entity.Article;
import com.w.article.entity.Cafe;
import com.w.article.service.ArticleService;
import com.w.article.service.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CafeService cafeService;

    private final ArticleService articleService;

    @Autowired
    public HomeController(CafeService cafeService, ArticleService articleService) {
        this.cafeService = cafeService;
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Cafe> cafeList = cafeService.selectCafeList();
        List<Article> articleList = articleService.selectArticleList();

        model.addAttribute("cafeList", cafeList);
        model.addAttribute("articleList", articleList);
        return "index";
    }
}
