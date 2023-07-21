package com.kakaotalk.gift.global.config.scheduler;

import com.kakaotalk.gift.global.config.event.ReceivedGiftCreatedEvent;
import com.kakaotalk.gift.global.config.redis.RedisAddQueue;
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
        Set<byte[]> keys = redisTemplate.getConnectionFactory().getConnection().keys("*".getBytes());
        for (byte[] key : keys) {
            String giftSerialCode = new String(key, 0, key.length);

            if (giftSerialCode.contains(":")) {
                Object quantity = redisTemplate.opsForValue().get(giftSerialCode);
                long availableQuantity = Long.parseLong(String.valueOf(quantity));
                String[] split = giftSerialCode.split(":");
                String secondKey = split[1];

                if (availableQuantity <= 0) {
                    log.info("선물하기 게임이 종료하였습니다.");
                    redisTemplate.delete(giftSerialCode);
                    break;
                }

                Set<Object> events = redisTemplate.opsForZSet().range(secondKey, MIN, MAX);
                publisher.publishEvent(new ReceivedGiftCreatedEvent(events));
                redisAddQueue.order();
            }
        }
    }
}
