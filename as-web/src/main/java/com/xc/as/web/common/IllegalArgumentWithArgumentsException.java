package com.xc.as.web.common;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yxc on 2016/11/26.
 */
public class IllegalArgumentWithArgumentsException extends IllegalArgumentException  {

    private static final long serialVersionUID = 1L;

    private String[] arguments;

    public IllegalArgumentWithArgumentsException(String message,
                                                 String... arguments) {
        super(message);
        this.arguments = arguments;
    }

    public String[] getArguments() {
        return arguments;
    }

    public String getFormattedMessage() {
        String message = super.getMessage();

        if (this.getArguments().length == 0) {
            return message;
        }

        Pattern pattern = Pattern.compile("\\{[\\d]+\\}");
        Matcher matcher = pattern.matcher(message);

        Map<String, Integer> tokens = new HashMap<String, Integer>();

        while (matcher.find()) {
            String token = matcher.group();
            tokens.put(token,
                    new Integer(token.substring(1, token.length() - 1)));
        }

        String detailMessage = message;
        for (String token : tokens.keySet()) {
            int index = tokens.get(token);

            if (index >= 0 && index < arguments.length) {
                detailMessage = detailMessage.replace(token, arguments[index]);
            }

        }

        return detailMessage;
    }
}
