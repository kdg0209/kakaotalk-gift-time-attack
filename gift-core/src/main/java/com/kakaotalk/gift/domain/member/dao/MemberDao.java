package com.kakaotalk.gift.domain.member.dao;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private BooleanExpression eqMemberId(String id) {
        if (id == null) return null;
        return member.id.eq(id);
    }
}
