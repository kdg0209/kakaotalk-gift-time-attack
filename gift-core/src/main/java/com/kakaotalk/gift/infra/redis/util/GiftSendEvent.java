package com.kakaotalk.gift.infra.redis.util;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GiftSendEvent {

    private String serialCode;
    private String giftName;
    private int giftQuantity;

    public GiftSendEvent(String serialCode, String giftName, int giftQuantity) {
        this.serialCode = serialCode;
        this.giftName = giftName;
        this.giftQuantity = giftQuantity;
    }
}
