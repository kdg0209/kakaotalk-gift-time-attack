package com.kakaotalk.gift.global.config.redis;

import com.kakaotalk.gift.global.util.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisPublisher {

    private final RedisTemplate redisTemplate;

    public void publish(EventType eventType, String message) {
        redisTemplate.convertAndSend(eventType.name(), message);
    }
}
