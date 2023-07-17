package com.kakaotalk.gift.domain.openroom.application;

import com.kakaotalk.gift.common.IntegrationTest;
import com.kakaotalk.gift.domain.member.application.MemberService;
import com.kakaotalk.gift.global.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@SpringBootApplication(scanBasePackages = "com.kakaotalk.gift")
class OpenRoomServiceTest extends IntegrationTest {

    @Autowired
    private OpenRoomService openRoomService;

    @Autowired
    private MemberService memberService;

    @Test
    void 정상적인_오픈채팅방을_생성하는_경우() {

        // given
        Long memberIdx = 1L;
        memberService.create("test");

        // when
        String result = openRoomService.create(memberIdx);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    void 존재하지않는_맴버가_오픈채팅방을_생성하는_경우_예외가_발생한다() {

        // given
        Long memberIdx = -1L;

        // when & then
        assertThatThrownBy(() -> openRoomService.create(memberIdx))
                .isInstanceOf(BusinessException.class);
    }
}