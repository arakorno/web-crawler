package com.github.apokydko.statistic;

public enum PredefinedJsFramework {
    ANGULAR("ng-"), REACT("react"), EMBER("ember"), VUE("vue"), BACKBONE("backbone"), JQUERY("jquery");
    private String sign;

    PredefinedJsFramework(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
