package com.github.apokydko.parser.impl;

import com.github.apokydko.statistic.PredefinedJsFramework;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CustomHtmlParserTest {

    private CustomHtmlParser customHtmlParser;

    @Before
    public void init() {
        customHtmlParser = new CustomHtmlParser();
    }

    @Test
    public void fetchLinksFromPageTest() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("google_page_data.html")) {
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            List<String> links = customHtmlParser.fetchLinksFromPage(result);
            Assert.assertEquals("https://en.wikipedia.org/wiki/Dog_type", links.get(5));
        }
    }

    @Test
    public void fetchScriptTagsDataFromPageTest() {
        String data = "<script type=\"text/javascript\" src=\"jquery.js\"></script>";
        String result = customHtmlParser.fetchTagsDataFromPage(data);
        Assert.assertTrue(result.contains("jquery"));
    }

    @Test
    public void fetchDivTagsDataFromPageTest() {
        String angularDivTag = "<div ng-controller=\"test\"></div>";
        String result = customHtmlParser.fetchTagsDataFromPage(angularDivTag);
        // detecting angular framework
        Assert.assertTrue(result.contains(PredefinedJsFramework.ANGULAR.getSign()));

        String emberDivTag = "<div class=\"ember-view\"></div>";
        result = customHtmlParser.fetchTagsDataFromPage(emberDivTag);
        // detecting ember framework
        Assert.assertTrue(result.contains(PredefinedJsFramework.EMBER.getSign()));

        String reactDivTag = "<div data-reactroot></div>";
        result = customHtmlParser.fetchTagsDataFromPage(reactDivTag);
        // detecting react framework
        Assert.assertTrue(result.contains(PredefinedJsFramework.REACT.getSign()));
    }
}