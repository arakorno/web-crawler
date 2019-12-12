package com.github.apokydko.parser;

import java.util.List;
import java.util.regex.Pattern;

public interface HtmlParser {
    List<String> fetchLinksFromPage(String response);

    List<String> parse(List<String> data);

    String fetchTagsDataFromPage(String response);

    List<String> parseByPattern(String response, Pattern pattern);
}
