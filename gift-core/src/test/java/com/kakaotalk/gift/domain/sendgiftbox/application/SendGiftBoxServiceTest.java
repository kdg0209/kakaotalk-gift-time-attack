package com.kakaotalk.gift.domain.sendgiftbox.application;

import com.kakaotalk.gift.common.IntegrationTest;
import com.kakaotalk.gift.domain.member.application.MemberService;
import com.kakaotalk.gift.domain.openroom.application.OpenRoomService;
import com.kakaotalk.gift.global.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@SpringBootApplication(scanBasePackages = "com.kakaotalk.gift")
class SendGiftBoxServiceTest extends IntegrationTest {

    @Autowired
    private SendGiftBoxService sendGiftBoxService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private OpenRoomService openRoomService;

    private Long memberIdx;
    private String openRoomCode;

    @BeforeEach
    void init() {
        String id = "test";
        memberIdx = memberService.create(id);
        openRoomCode = openRoomService.create(memberIdx);
    }

    @Test
    void 정상적으로_오픈채팅방에_선물을_발송하는_경우() {

        // given
        String giftName = "아이스 아메리카노";
        int giftQuantity = 10;

        // when
        String result = sendGiftBoxService.create(memberIdx, openRoomCode, giftName, giftQuantity);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    void 정상적인_맴버가_없는_경우에_오픈채팅방에_선물을_발송하는_경우_예외를_발생시킨다() {
        String giftName = "아이스 아메리카노";
        int giftQuantity = 10;
        Long lieMemberIdx = -1L;

        assertThatThrownBy(() -> sendGiftBoxService.create(lieMemberIdx, openRoomCode, giftName, giftQuantity))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    void 오픈채팅방이_없는_곳에_선물을_발송하는_경우_예외를_발생시킨다() {
        String giftName = "아이스 아메리카노";
        int giftQuantity = 10;
        String lieOpenRoomCode = "FALSE&FALSE&FALSE";

        assertThatThrownBy(() -> sendGiftBoxService.create(memberIdx, lieOpenRoomCode, giftName, giftQuantity))
                .isInstanceOf(BusinessException.class);
    }
}