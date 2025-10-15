package com.w.article.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.w.article.dto.ArticleDto;
import com.w.article.entity.Article;
import com.w.article.service.ArticleService;
import com.w.article.utils.RestApiClient;
import com.w.article.utils.UriUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/article")
public class ArticleApiController {

    private final ArticleService articleService;

    private final RestApiClient restApiClient;

    public ArticleApiController(ArticleService articleService, RestApiClient restApiClient) {
        this.articleService = articleService;
        this.restApiClient = restApiClient;
    }

    @PostMapping("/collect")
    public Map<String, Object> collectArticle(String cafeId, String menuId, Integer page, Integer pageSize) {
        if (!StringUtils.isNoneBlank(cafeId, menuId)) {
            return Map.of("result", false, "message", "parameter missing");
        }

        if (page == null || page < 0) {
            page = 0;
        }

        if (pageSize == null || pageSize < 0) {
            pageSize = 15;
        }

        String url = UriUtils.getListUrl(cafeId, menuId);

        Map<String, Object> params = Maps.newHashMap();
        params.put("page", page);
        params.put("pageSize", pageSize);
        params.put("sortBy", "TIME");

        Map<String, Object> resultMap = restApiClient.get(url, params, Map.class);
        Map<String, Object> articleMap = (Map<String, Object>) resultMap.get("result");
        List<Map<String, Object>> articleList = (List<Map<String, Object>>) articleMap.get("articleList");

        List<ArticleDto> resultList = Lists.newArrayList();
        for (Map<String, Object> map : articleList) {
            Map<String, Object> itemMap = (Map<String, Object>) map.get("item");
            Integer articleId = (Integer) itemMap.get("articleId");
            String subject = (String) itemMap.get("subject");
            String summary = (String) itemMap.get("summary");

            Timestamp writeDateTimestamp = new Timestamp((Long) itemMap.get("writeDateTimestamp"));
            String detailUrl = UriUtils.getDetailUrl(cafeId, menuId, String.valueOf(articleId));

            ArticleDto articleDto = new ArticleDto();
            articleDto.setCafeId(cafeId);
            articleDto.setArticleId(String.valueOf(articleId));
            articleDto.setSubject(subject);
            articleDto.setSummary(summary);
            articleDto.setWriteDateTimestamp(writeDateTimestamp);
            articleDto.setDetailUrl(detailUrl);
            resultList.add(articleDto);

            if (articleService.existsByCafeIdAndArticleId(cafeId, articleDto.getArticleId())) {
                continue;
            }
            articleService.createArticle(Article.fromDto(articleDto));
        }

        return Map.of("result", true, "message", "insert success", "size", resultList.size());
    }
}

