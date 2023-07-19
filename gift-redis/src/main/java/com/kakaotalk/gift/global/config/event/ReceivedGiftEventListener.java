package com.kakaotalk.gift.global.config.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kakaotalk.gift.domain.receivedgiftbox.application.ReceivedGiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReceivedGiftEventListener {

    private final ReceivedGiftService receivedGiftService;

    @EventListener(classes = ReceivedGiftCreatedEvent.class)
    public void handle(ReceivedGiftCreatedEvent event) throws JsonProcessingException {
        receivedGiftService.create(event.getEvents());
    }
}
