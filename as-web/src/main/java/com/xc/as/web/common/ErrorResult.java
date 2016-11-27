package com.xc.as.web.common;

/**
 * Created by yxc on 2016/11/26.
 */
public class ErrorResult {
    private String code;
    private String[] arguments;

    public ErrorResult(String code, String... arguments) {
        super();
        this.code = code;
        this.arguments = arguments;
    }

    public String getCode() {
        return code;
    }

    public String[] getArguments() {
        return arguments;
    }
}
