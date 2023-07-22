package com.kakaotalk.gift.domain.receivedgiftbox.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kakaotalk.gift.domain.receivedgiftbox.dao.ReceivedGiftBoxRepository;
import com.kakaotalk.gift.domain.receivedgiftbox.domain.ReceivedGiftBox;
import com.kakaotalk.gift.domain.sendgiftbox.dao.SendGiftBoxDao;
import com.kakaotalk.gift.domain.sendgiftbox.domian.SendGiftBox;
import com.kakaotalk.gift.infra.redis.util.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReceivedGiftService {

    private final RedisTemplate redisTemplate;
    private final SendGiftBoxDao sendGiftBoxDao;
    private final ReceivedGiftBoxRepository receivedGiftBoxRepository;

    public void create(Set<Object> events) throws JsonProcessingException {
        for (Object event : events) {
            if (event instanceof Event) {
                Event e = (Event) event;
                String giftSerialCode = e.getGiftSerialCode();
                String memberId = e.getMemberId();
                String secondKey = e.secondKey();
                Optional<SendGiftBox> optionalSendGiftBox = sendGiftBoxDao.findByGiftSerialCode(giftSerialCode);

                optionalSendGiftBox.ifPresent(sendGiftBox -> {
                            if (sendGiftBox.hasAvailableQuantity()) {
                                sendGiftBox.decreaseAvailableQuantity();
                                ReceivedGiftBox receivedGiftBox = new ReceivedGiftBox(memberId, sendGiftBox);
                                receivedGiftBoxRepository.save(receivedGiftBox);
                                log.info("{} 님이 기프티콘에 당첨되었습니다.", e.getMemberId());
                                decreaseAvailableQuantity(giftSerialCode); // 수량 감소
                                removeKey(secondKey, event);               // 게임 당첨자 redis에서 삭제
                            }
                        }
                );
            }
        }
    }

    private void decreaseAvailableQuantity(String giftSerialCode) {
        Long quantity = redisTemplate.opsForValue().decrement(giftSerialCode);
        log.info("남은 수량: "  + quantity);
    }

    private void removeKey(String key, Object event) {
        redisTemplate.opsForZSet().remove(key, event);
    }
}
