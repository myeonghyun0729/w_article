package com.w.article.controller;

import com.w.article.entity.Cafe;
import com.w.article.service.CafeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cafe")
public class CafeController {

    private final CafeService cafeService;

    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @GetMapping("/list")
    public String cafeList(Model model) {
        List<Cafe> cafeList = cafeService.selectCafeList();

        model.addAttribute("cafeList", cafeList);
        return "cafe-list";
    }
}

