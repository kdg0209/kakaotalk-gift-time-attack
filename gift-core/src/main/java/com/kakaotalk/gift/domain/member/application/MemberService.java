package com.kakaotalk.gift.domain.member.application;

import com.kakaotalk.gift.domain.member.dao.MemberDao;
import com.kakaotalk.gift.domain.member.dao.MemberRepository;
import com.kakaotalk.gift.domain.member.domain.Member;
import com.kakaotalk.gift.global.exception.BusinessException;
import com.kakaotalk.gift.global.exception.ErrorCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberDao memberDao;
    private final MemberRepository memberRepository;

    public Long create(@NonNull String id) {
        boolean existMemberId = memberDao.existMemberId(id);

        if (existMemberId) {
            throw new BusinessException(ErrorCode.MEMBER_ID_DUPLICATED_EXCEPTION);
        }

        Member member = new Member(id);
        memberRepository.save(member);
        return member.getIdx();
    }
}
