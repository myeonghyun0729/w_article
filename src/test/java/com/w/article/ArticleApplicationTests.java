package com.w.article;

import com.google.common.collect.Maps;
import com.w.article.config.RestClientProperties;
import com.w.article.dto.ArticleDto;
import com.w.article.utils.JsonUtils;
import com.w.article.utils.RestApiClient;
import com.w.article.utils.UriUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ArticleApplicationTests {

	@Autowired
	private RestClientProperties restClientProperties;


	@Autowired
	private RestApiClient restApiClient;

	@Test
	void contextLoads() {

	}

	@Test
	void crTest() throws IOException {
		String cafeId = "15903231";
		String menuId = "515";
		String url = UriUtils.getListUrl(cafeId, menuId);

		Map<String, Object> params = Maps.newHashMap();
		params.put("page", 1);
		params.put("pageSize", 2);
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
			articleDto.setArticleId(String.valueOf(articleId));
			articleDto.setSubject(subject);
			articleDto.setSummary(summary);
			articleDto.setWriteDateTimestamp(writeDateTimestamp);
			articleDto.setDetailUrl(detailUrl);
			resultList.add(articleDto);
		}
		System.out.println(JsonUtils.writeToString(resultList));
	}

}
