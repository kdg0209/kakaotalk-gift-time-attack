package com.kakaotalk.gift.domain.receivedgiftbox.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaotalk.gift.global.util.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Service
@Transactional
@RequiredArgsConstructor
public class ReceivedGiftService {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public void create(Set<Object> events) throws JsonProcessingException {
        for (Object event : events) {
            JsonNode jsonNode = OBJECT_MAPPER.readTree(event.toString());
            String memberIdx = OBJECT_MAPPER.readTree(jsonNode.asText()).get("memberIdx").asText();
            String participationCode = OBJECT_MAPPER.readTree(jsonNode.asText()).get("participationCode").asText();

            redisTemplate.opsForZSet().remove(EventType.KAKAO_GIFT.name(), memberIdx);
        }
    }
}
