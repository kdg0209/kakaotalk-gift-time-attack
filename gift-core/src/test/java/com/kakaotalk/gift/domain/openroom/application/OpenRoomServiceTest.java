package com.kakaotalk.gift.domain.openroom.application;

import com.kakaotalk.gift.common.IntegrationTest;
import com.kakaotalk.gift.domain.member.application.MemberService;
import com.kakaotalk.gift.global.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

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
        Long memberIdx = memberService.create("test");

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

    @Test
    void 정상적인_오픈채팅방에_입장하는_경우() {

        // given
        Long memberAIdx = memberService.create("test A");
        String participationCode = openRoomService.create(memberAIdx);
        Long memberBIdx = memberService.create("test B");

        // when
        Long result = openRoomService.join(memberBIdx, participationCode);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    void 오픈채팅방_참여코드가_옳바르지_않는_경우_예외를_발생시킨다() {

        // 정상적인 오픈 채팅방 생성
        Long memberAIdx = memberService.create("test A");
        openRoomService.create(memberAIdx);

        Long memberBIdx = memberService.create("test B");
        String participationCode = "1231231";

        assertThatThrownBy(() -> openRoomService.join(memberBIdx, participationCode))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    void 이미_참여한_오픈채팅방이라면_예외가_발생한다() {
        // 정상적인 오픈 채팅방 생성
        Long memberAIdx = memberService.create("test A");
        String participationCode = openRoomService.create(memberAIdx);

        // 오픈채팅방에 참가
        Long memberBIdx = memberService.create("test B");
        openRoomService.join(memberBIdx, participationCode);

        // 동일한 오픈채팅방에 또 참가
        assertThatThrownBy(() -> openRoomService.join(memberBIdx, participationCode))
                .isInstanceOf(BusinessException.class);
    }
}