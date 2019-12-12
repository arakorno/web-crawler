package com.github.apokydko.statistic;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

public class JsFrameworkStatistic {
    public static Map<PredefinedJsFramework, Integer> calculate(List<String> data) {
        Map<PredefinedJsFramework, Integer> topFrameworks = Arrays.stream(PredefinedJsFramework.values())
                .collect(Collectors.toMap(identity(), p -> 0));
        data.forEach(d -> topFrameworks.entrySet().stream().filter(f -> d.contains(f.getKey().getSign().toLowerCase()))
                .forEach(f -> topFrameworks.put(f.getKey(), f.getValue() + 1)));
        return topFrameworks;
    }
}
