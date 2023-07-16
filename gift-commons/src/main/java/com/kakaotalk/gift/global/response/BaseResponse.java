package com.kakaotalk.gift.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import static com.kakaotalk.gift.global.util.MessageSourceUtils.getMessage;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    private Integer status;
    private T result;
    private String resultMessage;

    public BaseResponse(ResponseStatus status) {
        this.status = status.getStatus();
        this.resultMessage = getMessage(status);
    }

    public BaseResponse(ResponseStatus status, T result) {
        this.status = status.getStatus();
        this.result = result;
        this.resultMessage = getMessage(status);
    }
}