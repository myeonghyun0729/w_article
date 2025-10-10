package com.w.article.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RestClientLoggingInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger logger = LogManager.getLogger(RestClientLoggingInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) {
        logger.debug("Request URI: {} ", request.getURI());
        logger.debug("Request Method: {} ", request.getMethod());
        logger.debug("Request Headers: {} ", request.getHeaders());

        ClientHttpResponse response = null;
        try {
            long start = System.currentTimeMillis();
            response = execution.execute(request, body);
            long duration = System.currentTimeMillis() - start;

            logger.debug("Response Status: {}", response.getStatusCode());
            logger.debug("Response Text: {}", response.getStatusText());
            logger.debug("==================== Request End [{}ms] ======================", duration);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return response;
    }

}