package com.w.article.service;

import com.w.article.dto.CafeDto;
import com.w.article.entity.Cafe;
import com.w.article.repository.CafeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CafeService {

    private final CafeRepository cafeRepository;

    public CafeService(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    @Transactional
    public List<Cafe> selectCafeList() {
        return cafeRepository.findAll();
    }

    @Transactional
    public Cafe createCafe(Cafe cafe) {
        return cafeRepository.save(cafe);
    }
}

