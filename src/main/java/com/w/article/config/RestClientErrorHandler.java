package com.w.article.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

public class RestClientErrorHandler implements ResponseErrorHandler {
    private static final Logger logger = LogManager.getLogger(RestClientErrorHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse response) {
        try {
            HttpStatusCode statusCode = response.getStatusCode();
            return statusCode.isError();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is5xxServerError()) {
            logger.error("is5xxServerError url : {}, method : {}, response code : {}, text : {}", url, method, response.getStatusCode(), response.getStatusText());
        } else if (response.getStatusCode().is4xxClientError()) {
            // handle CLIENT_ERROR
            logger.error("is4xxClientError url : {}, method : {}, response code : {}, text : {}", url, method, response.getStatusCode(), response.getStatusText());
            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new FileNotFoundException();
            }
        }
    }
}