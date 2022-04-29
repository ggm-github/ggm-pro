// Copyright 2016 Baidu Inc. All rights reserved.

package com.uway.sports.common.utils;

/**
 * The rest response factory.
 *
 * @author Alex Lu (lufei@baidu.com)
 */
public class RestResponseFactory {

    private static final RestResponse SUCCESS_EMPTY_RESPONSE = newSuccessfulResponse(null);

    public static <T> RestResponse<T, ?> newSuccessfulResponse(T result) {
        return new RestResponse.Builder<T, Object>()
                .withSuccess(true)
                .withResult(result)
                .withMessage(null)
                .build();
    }

    public static <T> RestResponse<T, ?> newSuccessfulEmptyResponse() {
        return SUCCESS_EMPTY_RESPONSE;
    }

    public static <M> RestResponse newFailedResponse(M message) {
        return new RestResponse.Builder<Object, M>()
                .withSuccess(false)
                .withResult(null)
                .withMessage(message)
                .build();
    }

}
