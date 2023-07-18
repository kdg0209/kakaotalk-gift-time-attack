package com.kakaotalk.gift.domain.openroom.dao;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kakaotalk.gift.domain.openroom.domain.QOpenRoomMapping.openRoomMapping;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OpenRoomMappingDao {

    private final JPAQueryFactory queryFactory;

    public boolean isRoomAlreadyJoined(Long openRoomIdx, Long memberIdx) {
        Integer result = queryFactory
                .selectOne()
                .from(openRoomMapping)
                .where(
                        eqOpenRoomIdx(openRoomIdx),
                        eqMemberIdx(memberIdx)
                )
                .fetchFirst();

        return result != null;
    }

    private BooleanExpression eqOpenRoomIdx(Long openRoomIdx) {
        if (openRoomIdx == null) return null;
        return openRoomMapping.openRoom.idx.eq(openRoomIdx);
    }

    private BooleanExpression eqMemberIdx(Long memberIdx) {
        if (memberIdx == null) return null;
        return openRoomMapping.member.idx.eq(memberIdx);
    }
}
