package com.example.app.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CustomHttpRequest {

    private String hostName;
    private Charset charsetBody = StandardCharsets.UTF_8;
    private String requestBody;
    private Map<String, String> requestHeaders;
    @Getter
    private Map<String, String> responseHeader;
    @Getter
    private int responseStatusCode;
    @Getter
    private String responseBody;

    public CustomHttpRequest(String hostName, Charset charsetBody, String requestBody, Map<String, String> requestHeaders) {
        this.hostName = hostName;
        this.charsetBody = charsetBody;
        this.requestBody = requestBody;
        this.requestHeaders = requestHeaders;
    }

    public CustomHttpRequest(String hostName, Charset charsetBody, Map<String, String> requestHeaders) {
        this.hostName = hostName;
        this.charsetBody = charsetBody;
        this.requestHeaders = requestHeaders;
    }

    public CustomHttpRequest(String hostName, Charset charset) {
        this.hostName = hostName;
        this.charsetBody = charsetBody;
    }

    public void sendGetRequest() {
        RequestConfig requestConfig = RequestConfig.custom().build();

        HttpGet httpGet = new HttpGet(hostName);
        httpGet.setConfig(requestConfig);

        log.info("Отправка GET запроса: " + hostName);
        if (requestHeaders != null)
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                httpGet.addHeader(header.getKey(), header.getValue());
                log.info("request header -> " + header.getKey() + ": " + header.getValue());
            }
        sendRequest(httpGet);
    }

    public void sendPostRequest() {
        RequestConfig requestConfig = RequestConfig.custom().build();

        HttpPost httpPost = new HttpPost(hostName);
        httpPost.setEntity(new StringEntity(requestBody, charsetBody));
        httpPost.setConfig(requestConfig);

        log.info("Отправка POST запроса: " + hostName);
        if (requestHeaders != null)
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                httpPost.addHeader(header.getKey(), header.getValue());
                log.info("request header -> " + header.getKey() + ": " + header.getValue());
            }

        sendRequest(httpPost);
    }

    private <T> void sendRequest(T request) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse resp = httpClient.execute((HttpUriRequest) request)) {

            responseBody = handleResponseBody(resp);
            responseHeader = handleResponseHeader(resp);
            responseStatusCode = handleStatusCode(resp);

            log.info("response body: " + responseBody.length());
            log.info("response header: " + responseHeader);
            log.info("response status: " + responseStatusCode);
        } catch (IOException e) {
            log.error(String.valueOf(e));
        }
    }

    private String handleResponseBody(HttpResponse response) {
        try {
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            log.error(String.valueOf(e));
        }
        return "";
    }

    private int handleStatusCode(HttpResponse response) {
        return response.getStatusLine().getStatusCode();
    }

    private Map<String, String> handleResponseHeader(HttpResponse response) {
        Map<String, String> headers = new HashMap<>();
        for (Header header : response.getAllHeaders()) {
            headers.put(header.getName(), header.getValue());
        }
        return headers;
    }
}
