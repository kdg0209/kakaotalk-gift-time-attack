package com.kakaotalk.gift.domain.receivedgiftbox.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kakaotalk.gift.infra.redis.util.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReceivedGiftService {

    private final RedisTemplate redisTemplate;

    public void create(Set<Object> events) throws JsonProcessingException {
        for (Object event : events) {
            if (event instanceof Event) {
                Event e = (Event) event;
                log.info("{} 님이 기프티콘에 당첨되었습니다.", e.getMemberId());
                redisTemplate.opsForValue().decrement(e.getGiftSerialCode());
                redisTemplate.opsForZSet().remove(e.secondKey(), event);
            }
        }
    }
}
