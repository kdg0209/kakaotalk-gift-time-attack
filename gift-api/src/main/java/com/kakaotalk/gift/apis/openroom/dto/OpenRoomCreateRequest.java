package com.kakaotalk.gift.apis.openroom.dto;

import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
public class OpenRoomCreateRequest {

    @Positive
    private long memberIdx;
}
