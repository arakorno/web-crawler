package com.github.apokydko.http.client.impl;

import com.github.apokydko.http.client.HttpClientService;
import com.github.apokydko.http.client.HttpRequestBuilder;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class HttpClientServiceImpl implements HttpClientService {
    public static final String EMPTY_RESULT = "";
    private final ExecutorService executor;

    public HttpClientServiceImpl() {
        final int POOL_SIZE = 20;
        executor = Executors.newFixedThreadPool(POOL_SIZE);
    }

    @Override
    public String sendRequest(String url) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequestBuilder.build(url);
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception e) {
            return EMPTY_RESULT;
        }
    }

    @Override
    public List<String> sendManyRequests(List<String> urls) {
        List<CompletableFuture<String>> futures = urls.stream().map(url -> CompletableFuture
                .supplyAsync(() -> sendRequest(url), executor).exceptionally(exception -> EMPTY_RESULT))
                .collect(Collectors.toList());
        return futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    @Override
    public ExecutorService getExecutor() {
        return executor;
    }
}
