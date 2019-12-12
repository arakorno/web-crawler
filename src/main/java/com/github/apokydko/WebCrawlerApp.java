package com.github.apokydko;

import com.github.apokydko.http.client.HttpClientService;
import com.github.apokydko.http.client.impl.HttpClientServiceImpl;
import com.github.apokydko.parser.HtmlParser;
import com.github.apokydko.parser.impl.CustomHtmlParser;
import com.github.apokydko.statistic.JsFrameworkStatistic;
import com.github.apokydko.statistic.PredefinedJsFramework;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class WebCrawlerApp {
    private static final String GOOGLE_SEARCH_URL = "https://www.google.com/search?q=";

    public void printTopJsFrameworks() throws Exception {
        System.out.println("Please, enter searching word:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String keyWord = reader.readLine();
        HttpClientService httpClientService = new HttpClientServiceImpl();
        String googlePageResult = httpClientService
                .sendRequest(GOOGLE_SEARCH_URL + URLEncoder.encode(keyWord, StandardCharsets.UTF_8));
        HtmlParser htmlParser = new CustomHtmlParser();
        List<String> links = htmlParser.fetchLinksFromPage(googlePageResult);
        List<String> linkResponses = httpClientService.sendManyRequests(links);
        List<String> parsedResponses = htmlParser.parse(linkResponses);
        System.out.println(parsedResponses);
        Map<PredefinedJsFramework, Integer> topFrameworks = JsFrameworkStatistic.calculate(parsedResponses);
        httpClientService.getExecutor().shutdown();
        System.out.println("List of most used js frameworks:");
        topFrameworks.entrySet().stream()
                .sorted(Map.Entry.<PredefinedJsFramework, Integer> comparingByValue().reversed())
                .forEach(e -> System.out.println(e.getKey().name() + " : " + e.getValue()));
    }

    public static void main(String[] args) throws Exception {
        WebCrawlerApp webCrawlerApp = new WebCrawlerApp();
        webCrawlerApp.printTopJsFrameworks();
    }
}
