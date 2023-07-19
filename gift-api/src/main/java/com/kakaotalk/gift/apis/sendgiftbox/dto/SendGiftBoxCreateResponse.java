package com.kakaotalk.gift.apis.sendgiftbox.dto;

import lombok.Getter;

@Getter
public class SendGiftBoxCreateResponse {

    private final long sendGiftBoxIdx;

    public SendGiftBoxCreateResponse(long sendGiftBoxIdx) {
        this.sendGiftBoxIdx = sendGiftBoxIdx;
    }
}
