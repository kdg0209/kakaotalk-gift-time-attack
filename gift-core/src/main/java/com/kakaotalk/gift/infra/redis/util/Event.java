package com.kakaotalk.gift.infra.redis.util;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Event {

    private String memberId;
    private String participationCode;

    public Event(String memberId, String participationCode) {
        this.memberId = memberId;
        this.participationCode = participationCode;
    }
}
