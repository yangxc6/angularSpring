package com.xc.as.web.common;

/**
 * Created by yxc on 2016/11/26.
 */
public class IllegalStateWithArgumentsException extends IllegalStateException {

    private static final long serialVersionUID = 1L;

    private String[] arguments;

    public IllegalStateWithArgumentsException(String message,
                                              String... arguments) {
        super(message);
        this.arguments = arguments;
    }

    public String[] getArguments() {
        return arguments;
    }
}
