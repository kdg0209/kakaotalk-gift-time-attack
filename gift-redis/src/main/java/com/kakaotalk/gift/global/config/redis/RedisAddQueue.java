package com.kakaotalk.gift.global.config.redis;

import com.kakaotalk.gift.infra.redis.util.Event;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
    public void addQueue(String giftSerialCode, String memberId, String participationCode) {
        long timeMillis = System.currentTimeMillis();
        Event event = new Event(giftSerialCode, memberId, participationCode);

        redisTemplate.opsForZSet().add(event.secondKey(), event, timeMillis);
        log.info("대기열에 추가 - {} [{}]", event.getMemberId(), timeMillis);
    }

    public void addGiftInformation(String giftSerialCode, String giftName, int giftQuantity) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(giftSerialCode, giftQuantity);

        log.info("{} 선물이 도착하였습니다. 참여코드 - {}", giftName, giftSerialCode);
    }

    public void order() {
        Set<byte[]> keys = redisTemplate.getConnectionFactory().getConnection().keys("*".getBytes());
        for (byte[] key : keys) {
            String giftSerialCode = new String(key, 0, key.length);

            if (giftSerialCode.contains(":")) {
                String[] split = giftSerialCode.split(":");
                String secondKey = split[1];
                Set<Object> queue = redisTemplate.opsForZSet().range(secondKey, MIN, Long.MAX_VALUE);

                for (Object o : queue) {
                    if (o instanceof Event) {
                        Event e = (Event) o;
                        Long rank = redisTemplate.opsForZSet().rank(e.secondKey(), o);

                        log.info("'{}'님의 현재 대기열은 {}명 남았습니다.", e.getMemberId(), rank);
                    }
                }
            }
        }
    }
}
