package com.kakaotalk.gift.global.exception;

public class CustomIllegalArgumentException extends RuntimeException {

    private ErrorCode errorCode;
    private String[] messages;

    public CustomIllegalArgumentException(ErrorCode errorCode, String[] messages) {
        this.errorCode = errorCode;
        this.messages = messages;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String[] getMessages() {
        return messages;
    }
}
