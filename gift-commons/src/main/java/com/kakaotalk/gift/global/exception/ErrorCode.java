package com.kakaotalk.gift.global.exception;

public enum ErrorCode {

    MEMBER_ID_DUPLICATED_EXCEPTION("MEMBER_ID_DUPLICATED_EXCEPTION"),
    MEMBER_NOT_FOUND_EXCEPTION("MEMBER_NOT_FOUND_EXCEPTION"),
    OPEN_ROOM_NOT_FOUND_EXCEPTION("OPEN_ROOM_NOT_FOUND_EXCEPTION"),
    OPEN_ROOM_ALREADY_JOINED_EXCEPTION("OPEN_ROOM_ALREADY_JOINED_EXCEPTION")
    ;

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
