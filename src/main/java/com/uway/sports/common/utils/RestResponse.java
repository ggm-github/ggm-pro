// Copyright 2016 Baidu Inc. All rights reserved.

package com.uway.sports.common.utils;

import com.google.common.base.Objects;

/**
 * The rest response.
 *
 * @param <R> The result type.
 * @param <M> The message type.
 *
 * @author Alex Lu (lufei@baidu.com)
 */
public class RestResponse<R, M> implements  RestResultResponse<R> {

    static final class Builder<R, M> {
        private boolean success;
        private R result;
        private M message;

        public Builder<R, M> withSuccess(boolean success) {
            this.success = success;
            return this;
        }

        public Builder<R, M> withResult(R result) {
            this.result = result;
            return this;
        }

        public Builder<R, M> withMessage(M message) {
            this.message = message;
            return this;
        }

        public RestResponse<R, M> build() {
            RestResponse<R, M> response = new RestResponse<R, M>();
            response.success = success;
            response.result = result;
            response.message = message;
            return response;
        }
    }

    RestResponse() {
    }

    private boolean success;
    private R result = null;
    private M message = null;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public R getResult() {
        return result;
    }

    public void setResult(R result) {
        this.result = result;
    }

    public M getMessage() {
        return message;
    }

    public void setMessage(M message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{RestResponse,success=");
        builder.append(success);
        builder.append(",result=");
        builder.append(result);
        builder.append(",message=");
        builder.append(message);
        builder.append(",page=");
        builder.append("}");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RestResponse)) {
            return false;
        }
        RestResponse<?, ?> that = (RestResponse<?, ?>) o;
        return success == that.success
                && Objects.equal(result, that.result)
                && Objects.equal(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(success, result, message);
    }
}
