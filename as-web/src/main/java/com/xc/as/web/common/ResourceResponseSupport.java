package com.xc.as.web.common;

import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Created by yxc on 2016/11/26.
 */
public class ResourceResponseSupport {

    protected RestResultResponse buildErrorRestResultResponse(Exception ex) {
        Throwable reason = ex.getCause();

        if (reason == null) {
            reason = ex;
        }

        RestResultResponse response;

        if (reason instanceof IllegalArgumentException
                || reason instanceof IllegalStateException) {
            if (reason instanceof IllegalArgumentWithArgumentsException) {
                IllegalArgumentWithArgumentsException typedException = (IllegalArgumentWithArgumentsException) reason;

                response = new RestResultResponse(RestResultStatus.ERROR,
                        new ErrorResult(typedException.getFormattedMessage(),
                                typedException.getArguments()));
            } else if (reason instanceof IllegalStateWithArgumentsException) {
                IllegalStateWithArgumentsException typedException = (IllegalStateWithArgumentsException) reason;

                response = new RestResultResponse(RestResultStatus.ERROR,
                        new ErrorResult(typedException.getMessage(),
                                typedException.getArguments()));
            } else {
                response = new RestResultResponse(RestResultStatus.ERROR,
                        new ErrorResult(reason.getMessage()));
            }
        } else {
            response = new RestResultResponse(RestResultStatus.ERROR,
                    new ErrorResult(ex.getMessage()));
        }

        return response;
    }

    protected RestResultResponse buildSuccessRestResultResponse(Object aResult) {
        return new RestResultResponse(RestResultStatus.SUCCESS, aResult);
    }

    protected RestResultResponse buildSuccessRestResultResponse(List<Class<?>> classList, HttpStatus ok) {
        return new RestResultResponse(RestResultStatus.SUCCESS, "");
    }

}
