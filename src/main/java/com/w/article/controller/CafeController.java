package com.w.article.controller;

import com.w.article.dto.CafeDto;
import com.w.article.entity.Cafe;
import com.w.article.service.CafeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cafe")
public class CafeController {

    private final CafeService cafeService;

    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @GetMapping("/list")
    public List<Cafe> getCafeList() {
        return cafeService.selectCafeList();
    }

    @PostMapping("/create")
    public Cafe createCafe(@RequestBody CafeDto dto) {
        return cafeService.createCafe(Cafe.fromDto(dto));
    }
}

