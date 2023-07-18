package com.kakaotalk.gift.domain.sendgiftbox.domian;

import com.kakaotalk.gift.domain.member.domain.Member;
import com.kakaotalk.gift.global.exception.CustomIllegalArgumentException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SendGiftBoxTest {

    @Test
    void 정상적인_보낸선물함을_생성하는_경우() {

        // given
        Member member = new Member("test");
        String openRoomCode = "2307172237D26M4VDI";
        String giftName = "아이스 아메리카노";
        int giftQuantity = 10;

        // when
        SendGiftBox result = new SendGiftBox(member, openRoomCode, giftName, giftQuantity);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    void 맴버가_NULL값인경우_예외를_발생시킨다() {
        // given
        Member member = null;
        String openRoomCode = "2307172237D26M4VDI";
        String giftName = "아이스 아메리카노";
        int giftQuantity = 10;

        // when & then
        assertThatThrownBy(() -> new SendGiftBox(member, openRoomCode, giftName, giftQuantity))
                .isInstanceOf(CustomIllegalArgumentException.class);
    }

    @Test
    void 오픈채팅방_코드가_NULL값인경우_예외를_발생시킨다() {
        // given
        Member member = new Member("test");
        String openRoomCode = null;
        String giftName = "아이스 아메리카노";
        int giftQuantity = 10;

        // when & then
        assertThatThrownBy(() -> new SendGiftBox(member, openRoomCode, giftName, giftQuantity))
                .isInstanceOf(CustomIllegalArgumentException.class);
    }
}