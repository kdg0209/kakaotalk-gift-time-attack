package com.kakaotalk.gift.global.config.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaotalk.gift.global.util.Event;
import com.kakaotalk.gift.global.util.EventType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisAddQueue {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final RedisTemplate<String, Object> redisTemplate;

    @SneakyThrows
    public void addQueue(EventType eventType, String memberId, String participationCode) {
        long timeMillis = System.currentTimeMillis();
        Event event = new Event(memberId, participationCode);
        String value = OBJECT_MAPPER.writeValueAsString(event.toString());
        redisTemplate.opsForZSet().add(eventType.name(), value, timeMillis);
        log.info("대기열에 추가: memberId={}, timeMillis={}", event.getMemberIdx(), timeMillis);
    }
}
