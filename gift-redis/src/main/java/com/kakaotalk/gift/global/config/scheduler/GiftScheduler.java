package com.kakaotalk.gift.global.config.scheduler;

import com.kakaotalk.gift.global.config.event.ReceivedGiftCreatedEvent;
import com.kakaotalk.gift.global.config.redis.RedisAddQueue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class GiftScheduler {

    private static final long MIN = 0;
    private static final long MAX = 10;
    private static final long DELAY = 1000L; // 1s
    private static final String ALL_KEY = "*";
    private final RedisAddQueue redisAddQueue;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ApplicationEventPublisher publisher;

    @Scheduled(fixedDelay = DELAY)
    public void giftEventScheduler() {
        Set<byte[]> keys = redisTemplate.getConnectionFactory().getConnection().keys(ALL_KEY.getBytes());

        Iterator<byte[]> iterator = keys.iterator();
        while (iterator.hasNext()) {
            byte[] key = iterator.next();
            String giftSerialCode = new String(key, 0, key.length);

            if (giftSerialCode.contains(":")) {
                String secondKey = findSecondKey(giftSerialCode);
                Set<Object> events = redisTemplate.opsForZSet().range(secondKey, MIN, MAX);
                publisher.publishEvent(new ReceivedGiftCreatedEvent(events));
                redisAddQueue.order();
                verifyEndGame(iterator, giftSerialCode);
            }
        }
    }

    private String findSecondKey(String giftSerialCode) {
        String[] split = giftSerialCode.split(":");
        return split[1];
    }

    private void verifyEndGame(Iterator iterator, String giftSerialCode) {
        Object o = redisTemplate.opsForValue().get(giftSerialCode);
        if (o != null) {
            long quantity = Long.parseLong(o.toString());
            if (quantity <= 0) {
                log.info("선물하기 게임이 종료하였습니다. 남은 수량: "  + quantity);
                redisTemplate.delete(giftSerialCode);
                iterator.remove();
            }
        }
    }
}
