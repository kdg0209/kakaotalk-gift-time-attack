package com.kakaotalk.gift.global.exception;

public enum ErrorCode {

    MEMBER_ID_DUPLICATED_EXCEPTION("MEMBER_ID_DUPLICATED_EXCEPTION");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
