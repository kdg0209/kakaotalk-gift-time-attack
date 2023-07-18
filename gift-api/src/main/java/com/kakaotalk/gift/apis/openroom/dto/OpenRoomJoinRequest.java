package com.kakaotalk.gift.apis.openroom.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
public class OpenRoomJoinRequest {

    @Positive
    private long memberIdx;

    @NotBlank
    private String participationCode;
}
