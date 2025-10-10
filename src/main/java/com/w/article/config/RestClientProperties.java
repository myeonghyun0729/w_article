package com.w.article.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties("rest.client")
@Getter
@Setter
public class RestClientProperties {
    private final String baseUrl;
    private final Duration connectTimeout;
    private final Duration readTimeout;

    public RestClientProperties(String baseUrl, Duration connectTimeout, Duration readTimeout) {
        this.baseUrl = baseUrl;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }
}
