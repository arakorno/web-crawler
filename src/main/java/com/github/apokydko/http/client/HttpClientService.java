package com.github.apokydko.http.client;

import java.util.List;
import java.util.concurrent.ExecutorService;

public interface HttpClientService {

    String sendRequest(String url);

    List<String> sendManyRequests(List<String> urls);

    ExecutorService getExecutor();
}
