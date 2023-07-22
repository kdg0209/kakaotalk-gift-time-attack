package com.kakaotalk.gift.apis.sendgiftbox.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ReceivedGiftBoxCreateRequest {

    @NotBlank
    private String memberId;

    @NotBlank
    private String participationCode;

    @NotBlank
    private String giftSerialCode; // redis에서 저장중인 serialCode 형식에서 ex) 2307220010FA9HHIEX:2307220010J0NPV6IU
}
