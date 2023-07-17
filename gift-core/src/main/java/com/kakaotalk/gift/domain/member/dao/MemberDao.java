package com.kakaotalk.gift.domain.member.dao;

import com.kakaotalk.gift.domain.member.domain.Member;
import com.kakaotalk.gift.global.exception.BusinessException;
import com.kakaotalk.gift.global.exception.ErrorCode;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.kakaotalk.gift.domain.member.domain.QMember.member;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberDao {

    private final JPAQueryFactory queryFactory;

    public boolean existMemberId(String id) {
        Integer result = queryFactory
                .selectOne()
                .from(member)
                .where(
                        eqMemberId(id)
                )
                .fetchFirst();

        return result != null;
    }

    public Member findByIdx(Long idx) {
        Member result = queryFactory
                .selectFrom(member)
                .where(
                        eqMemberIdx(idx)
                )
                .fetchFirst();

        return Optional.ofNullable(result)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND_EXCEPTION));
    }

    private BooleanExpression eqMemberId(String id) {
        if (id == null) return null;
        return member.id.eq(id);
    }
    private BooleanExpression eqMemberIdx(Long idx) {
        if (idx == null) return null;
        return member.idx.eq(idx);
    }

}
