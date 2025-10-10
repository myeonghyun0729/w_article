package com.w.article.utils;

import org.apache.commons.collections4.MapUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class UrlBuilderUtils {

    public static String buildUrl(String url) {
        return buildUrl(url, null);
    }

    public static String buildUrl(String url, Map<String, Object> params) {
        UriComponentsBuilder builder;
        try {
            builder = UriComponentsBuilder.fromUriString(url);
        } catch (IllegalArgumentException e) {
            return url;
        }

        if (MapUtils.isEmpty(params)) {
            return builder.toUriString();
        }

        params.forEach((key, value) -> {
            if (!ObjectUtils.isEmpty(value)) {
                builder.queryParam(key, value);
            }
        });

        return builder.toUriString();
    }
}