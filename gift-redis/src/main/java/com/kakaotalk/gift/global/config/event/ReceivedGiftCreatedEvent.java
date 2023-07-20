package com.kakaotalk.gift.global.config.event;

import lombok.Getter;

import java.util.Set;

@Getter
public class ReceivedGiftCreatedEvent {

    private final Set<Object> events;

    public ReceivedGiftCreatedEvent(Set<Object> events) {
        this.events = events;
    }
}
