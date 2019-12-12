package com.github.apokydko.parser;

import java.util.regex.Pattern;

public class SearchPattern {
    public static final String SCRIPT_TAG_REGEX = "<script (.*?)</script>";
    public static final String DIV_TAG_REGEX = "<div (.*?)</div>";
    public static final Pattern SCRIPT_PATTERN = Pattern.compile(SCRIPT_TAG_REGEX);
    public static final Pattern DIV_PATTERN = Pattern.compile(DIV_TAG_REGEX);
    public static final String GOOGLE_SEARCH = "<a href=\"/url\\?q=(.*?)&amp;";
    public static final Pattern GOOGLE_SEARCH_PATTERN = Pattern.compile(GOOGLE_SEARCH);
}
