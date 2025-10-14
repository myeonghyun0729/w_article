package com.w.article.utils;

import com.google.common.collect.Maps;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
public class RestApiClient {

    private final RestClient restClient;

    private RestApiClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public <T> T get(String url, Class<T> responseType) {
        return get(url, Maps.newHashMap(), responseType);
    }

    public <T> T get(String url, Map<String, Object> params, Class<T> responseType) {
        return restClient.get()
                .uri(UrlBuilderUtils.buildUrl(url, params))
                .retrieve()
                .body(responseType);
    }

    public <T> T get(String url, Map<String, Object> params, ParameterizedTypeReference<T> responseType) {
        return restClient.get()
                .uri(UrlBuilderUtils.buildUrl(url, params))
                .retrieve()
                .body(responseType);
    }

    public <T> T post(String url, Map<String, Object> params, Class<T> responseType) {
        return post(url, params, new HashMap<>(), responseType);
    }

    public <T> T post(String url, Map<String, Object> params, Map<String, String> headers, Class<T> responseType) {
        return restClient.post()
                .uri(url)
                .body(params)
                .headers(httpHeaders -> {
                    httpHeaders.setAll(headers);
                })
                .retrieve()
                .body(responseType);
    }
}

