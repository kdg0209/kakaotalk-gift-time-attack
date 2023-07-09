package com.kakaotalk.gift.domain.gift.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gift")
@RequiredArgsConstructor
public class GiftApi {

    @GetMapping
    public String hello() {
        return "gift-api-hello";
    }
}
