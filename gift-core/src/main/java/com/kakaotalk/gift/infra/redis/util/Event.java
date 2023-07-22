package com.kakaotalk.gift.infra.redis.util;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Event {

    private String giftSerialCode;
    private String memberId;
    private String participationCode;

    public Event(String giftSerialCode, String memberId, String participationCode) {
        this.giftSerialCode = giftSerialCode;
        this.memberId = memberId;
        this.participationCode = participationCode;
    }

    public String firstKey() {
        return this.giftSerialCode.split(":")[0];
    }

    public String secondKey() {
        return this.giftSerialCode.split(":")[1];
    }
}
