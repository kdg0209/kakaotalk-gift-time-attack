package com.kakaotalk.gift.apis.member.dto;

import lombok.Getter;

@Getter
public class MemberCreateResponse {

    private final Long memberIdx;

    public MemberCreateResponse(Long memberIdx) {
        this.memberIdx = memberIdx;
    }
}
