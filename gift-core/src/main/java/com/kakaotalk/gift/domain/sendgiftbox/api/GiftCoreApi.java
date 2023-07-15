package com.kakaotalk.gift.domain.sendgiftbox.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gift-core")
@RequiredArgsConstructor
public class GiftCoreApi {

    @GetMapping
    public String hello() {
        return "gift-core-hello";
    }
}
