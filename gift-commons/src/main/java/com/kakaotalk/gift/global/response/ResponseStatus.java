package com.kakaotalk.gift.global.response;


public enum ResponseStatus {

    CODE_200(200),
    CODE_201(201),
    CODE_400(400),
    CODE_401(401),
    CODE_403(403),
    CODE_404(404),
    CODE_405(405),
    CODE_500(500);

    private Integer status;

    ResponseStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}