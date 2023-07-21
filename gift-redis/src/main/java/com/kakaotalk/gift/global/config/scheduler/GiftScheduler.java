package com.kakaotalk.gift.global.config.scheduler;

import com.kakaotalk.gift.global.config.event.ReceivedGiftCreatedEvent;
import com.kakaotalk.gift.global.config.redis.RedisAddQueue;
import com.kakaotalk.gift.infra.redis.util.EventType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class GiftScheduler {

    private static final long MIN = 0;
    private static final long MAX = 10;
    private static final long DELAY = 1000L; // 1s
    private final RedisAddQueue redisAddQueue;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ApplicationEventPublisher publisher;

    @Scheduled(fixedDelay = DELAY)
    public void giftEventScheduler() {
        Set<Object> events = redisTemplate.opsForZSet().range(EventType.KAKAO_GIFT.name(), MIN, MAX);
        publisher.publishEvent(new ReceivedGiftCreatedEvent(events));
        redisAddQueue.order();
    }
}
