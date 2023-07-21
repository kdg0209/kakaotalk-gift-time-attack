package com.kakaotalk.gift.global.config.redis;

import com.kakaotalk.gift.infra.redis.util.Event;
import com.kakaotalk.gift.infra.redis.util.EventType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * redis 대기열
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RedisAddQueue {

    private static final long MIN = 0;
    private static final long MAX = 10;
    private final RedisTemplate<String, Object> redisTemplate;

    @SneakyThrows
    public void addQueue(String memberId, String participationCode) {
        long timeMillis = System.currentTimeMillis();
        Event event = new Event(memberId, participationCode);
        redisTemplate.opsForZSet().add(EventType.KAKAO_GIFT.name(), event, timeMillis);
        log.info("대기열에 추가 - {} [{}]", event.getMemberId(), timeMillis);
    }

    public void order() {
        Set<Object> queue = redisTemplate.opsForZSet().range(EventType.KAKAO_GIFT.name(), MIN, Long.MAX_VALUE);

        for (Object o : queue) {
            if (o instanceof Event) {
                Long rank = redisTemplate.opsForZSet().rank(EventType.KAKAO_GIFT.name(), o);
                Event e = (Event) o;
                log.info("'{}'님의 현재 대기열은 {}명 남았습니다.", e.getMemberId(), rank);
            }
        }
    }
}
