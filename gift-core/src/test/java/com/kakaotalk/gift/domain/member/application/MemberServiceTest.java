package com.kakaotalk.gift.domain.member.application;

import com.kakaotalk.gift.domain.member.application.common.IntegrationTest;
import com.kakaotalk.gift.global.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
@SpringBootApplication(scanBasePackages = "com.kakaotalk.gift")
class MemberServiceTest extends IntegrationTest {

    @Autowired
    private MemberService memberService;

    @Test
    void 정상적인_맴버객체를_생성하는_경우() {

        // given
        String id = "test";

        // then
        Long result = memberService.create(id);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void 아이디가_null_인경우_NPE가_발생하는_경우() {

        // given
        String id = null;

        assertThatThrownBy(() -> memberService.create(id))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void 아이디가_중복되어_예외가_발생하는_경우() {

        // given
        String id1 = "test";
        memberService.create(id1);

        String id2 = "test";

        // when & then
        assertThatThrownBy(() -> memberService.create(id2))
                .isInstanceOf(BusinessException.class);
    }
}