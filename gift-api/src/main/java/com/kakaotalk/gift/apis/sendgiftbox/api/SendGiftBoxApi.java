package com.kakaotalk.gift.apis.sendgiftbox.api;

import com.kakaotalk.gift.apis.sendgiftbox.dto.SendGiftBoxCreateRequest;
import com.kakaotalk.gift.apis.sendgiftbox.dto.SendGiftBoxCreateResponse;
import com.kakaotalk.gift.domain.sendgiftbox.application.SendGiftBoxService;
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
@RequestMapping("/send-gift-box")
@RequiredArgsConstructor
public class SendGiftBoxApi {

    private final RedisAddQueue redisAddQueue;
    private final SendGiftBoxService sendGiftBoxService;

    @PostMapping
    public BaseResponse<SendGiftBoxCreateResponse> create(@Valid @RequestBody SendGiftBoxCreateRequest request) {
        String result = sendGiftBoxService.create(request.getMemberIdx(), request.getOpenRoomCode(), request.getGiftName(), request.getGiftQuantity());
        redisAddQueue.addGiftInformation(result, request.getGiftName(), request.getGiftQuantity());
        return new BaseResponse<>(CODE_201, new SendGiftBoxCreateResponse(result));
    }
}
