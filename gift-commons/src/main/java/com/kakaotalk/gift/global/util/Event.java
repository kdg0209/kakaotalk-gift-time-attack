package com.kakaotalk.gift.global.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.SneakyThrows;

@Getter
public class Event {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final String memberIdx;
    private final String participationCode;

    public Event(String memberIdx, String participationCode) {
        this.memberIdx = memberIdx;
        this.participationCode = participationCode;
    }

    @Override
    @SneakyThrows
    public String toString() {
        return OBJECT_MAPPER.writeValueAsString(this);
    }
}
