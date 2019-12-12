package com.github.apokydko.statistic;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class JsFrameworkStatisticTest {

    @Test
    public void calculate() {
        List<String> parsedList = List.of("<div ng-root>", "jquery.js  <div ng-controller>",
                "<div class=\"ember-view\">");
        Map<PredefinedJsFramework, Integer> result = JsFrameworkStatistic.calculate(parsedList);
        Assert.assertEquals(2, result.get(PredefinedJsFramework.ANGULAR).intValue());
        Assert.assertEquals(1, result.get(PredefinedJsFramework.JQUERY).intValue());
        Assert.assertEquals(1, result.get(PredefinedJsFramework.EMBER).intValue());
        Assert.assertEquals(0, result.get(PredefinedJsFramework.VUE).intValue());
        Assert.assertEquals(0, result.get(PredefinedJsFramework.BACKBONE).intValue());
    }
}