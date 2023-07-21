package com.kakaotalk.gift.apis.receivedgiftbox.api;

import com.kakaotalk.gift.apis.sendgiftbox.dto.ReceivedGiftBoxCreateRequest;
import com.kakaotalk.gift.global.config.redis.RedisAddQueue;
import com.kakaotalk.gift.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.kakaotalk.gift.global.response.ResponseStatus.CODE_201;

@RestController
@RequestMapping("/received-gift-box")
@RequiredArgsConstructor
public class ReceivedGiftBoxApi {

    private final RedisAddQueue redisAddQueue;

    @PostMapping
    public BaseResponse<Void> create(@Valid @RequestBody ReceivedGiftBoxCreateRequest request) {
        redisAddQueue.addQueue(request.getGiftSerialCode(), request.getMemberId(), request.getParticipationCode());
        return new BaseResponse<>(CODE_201);
    }
}
