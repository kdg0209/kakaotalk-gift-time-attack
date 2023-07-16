package com.kakaotalk.gift.apis.member.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class MemberCreateRequest {

    @NotBlank
    private String id;
}
