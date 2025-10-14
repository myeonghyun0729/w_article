package com.w.article.controller;

import com.w.article.entity.Article;
import com.w.article.entity.Cafe;
import com.w.article.service.ArticleService;
import com.w.article.service.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String home(Model model, @RequestParam(required = false) String keyword,
                       @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) {
            page = 0;
        }

        List<Cafe> cafeList = cafeService.selectCafeList();

        Page<Article> articleList = articleService.selectArticleList(keyword, page, 15);
        System.out.println("총 데이터 수: " + articleList.getTotalElements());
        System.out.println("총 페이지 수: " + articleList.getTotalPages());
        System.out.println("현재 페이지 번호: " + articleList.getNumber());
        System.out.println("페이지 크기: " + articleList.getSize());
        System.out.println("다음 페이지 존재 여부: " + articleList.hasNext());
        System.out.println("이전 페이지 존재 여부: " + articleList.hasPrevious());

        model.addAttribute("cafeList", cafeList);
        model.addAttribute("articleList", articleList);
        model.addAttribute("keyword", keyword);
        return "index";
    }
}
