package com.w.article.config;

import com.w.article.interceptor.RestClientLoggingInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.http.client.ClientHttpRequestFactoryBuilder;
import org.springframework.boot.http.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {

    private final RestClientProperties restClientProperties;

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl(restClientProperties.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .requestFactory(clientRequestFactory())
                .requestInterceptor(new RestClientLoggingInterceptor())
                .build();
    }

    private ClientHttpRequestFactory clientRequestFactory() {
        ClientHttpRequestFactorySettings settings = ClientHttpRequestFactorySettings.defaults()
                .withConnectTimeout(restClientProperties.getConnectTimeout())
                .withReadTimeout(restClientProperties.getReadTimeout());
        return ClientHttpRequestFactoryBuilder.detect().build(settings);
    }
}
