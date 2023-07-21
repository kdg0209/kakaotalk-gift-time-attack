package com.kakaotalk.gift.apis.sendgiftbox.dto;

import lombok.Getter;

@Getter
public class SendGiftBoxCreateResponse {

    private final String giftSerialCode;

    public SendGiftBoxCreateResponse(String giftSerialCode) {
        this.giftSerialCode = giftSerialCode;
    }
}
