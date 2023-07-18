package com.kakaotalk.gift.apis.sendgiftbox.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class SendGiftBoxCreateRequest {

    @NotNull
    @Positive
    private Long memberIdx;

    @NotBlank
    private String openRoomCode;

    @NotBlank
    private String giftName;

    @NotNull
    @Positive
    private int giftQuantity;
}
