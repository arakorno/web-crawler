package com.github.apokydko.http.client;

import java.net.URI;
import java.net.http.HttpRequest;

public class HttpRequestBuilder {
    private static final String charset = "UTF-8";
    private static final String userAgent = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";

    public static HttpRequest build(String url) {
        return HttpRequest.newBuilder().uri(URI.create(url)).setHeader("User-Agent", userAgent)
                .setHeader("Content-charset", charset).build();
    }
}
