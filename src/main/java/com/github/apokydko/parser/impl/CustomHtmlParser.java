package com.github.apokydko.parser.impl;

import com.github.apokydko.parser.HtmlParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.github.apokydko.parser.SearchPattern.*;

public class CustomHtmlParser implements HtmlParser {
    @Override
    public List<String> fetchLinksFromPage(String response) {
        return parseByPattern(response, GOOGLE_SEARCH_PATTERN);
    }

    @Override
    public List<String> parse(List<String> data) {
        return data.stream().map(this::fetchTagsDataFromPage).collect(Collectors.toList());
    }

    @Override
    public String fetchTagsDataFromPage(String response) {
        List<String> scriptTagData = parseByPattern(response, SCRIPT_PATTERN);
        List<String> divTagData = parseByPattern(response, DIV_PATTERN);
        scriptTagData.addAll(divTagData);
        return String.join("", scriptTagData);
    }

    @Override
    public List<String> parseByPattern(String response, Pattern pattern) {
        List<String> links = new ArrayList<>();
        Matcher m = pattern.matcher(response);
        while (m.find()) {
            links.add(m.group(1));
        }
        return links;
    }
}
