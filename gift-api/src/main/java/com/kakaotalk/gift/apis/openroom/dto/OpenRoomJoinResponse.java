package com.kakaotalk.gift.apis.openroom.dto;

import lombok.Getter;

@Getter
public class OpenRoomJoinResponse {

    private final long openRoomIdx;

    public OpenRoomJoinResponse(long openRoomIdx) {
        this.openRoomIdx = openRoomIdx;
    }
}
