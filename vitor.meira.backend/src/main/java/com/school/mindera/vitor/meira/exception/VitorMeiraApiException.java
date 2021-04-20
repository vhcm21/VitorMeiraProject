package com.school.mindera.vitor.meira.exception;

public class VitorMeiraApiException extends RuntimeException {
    public VitorMeiraApiException() {
    }

    public VitorMeiraApiException(String message) {
        super(message);
    }

    public VitorMeiraApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public VitorMeiraApiException(Throwable cause) {
        super(cause);
    }

    public VitorMeiraApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

