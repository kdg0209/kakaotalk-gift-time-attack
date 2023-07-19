package com.kakaotalk.gift.apis.sendgiftbox.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ReceivedGiftBoxCreateRequest {

    @NotBlank
    private String memberId;

    @NotBlank
    private String participationCode;
}
