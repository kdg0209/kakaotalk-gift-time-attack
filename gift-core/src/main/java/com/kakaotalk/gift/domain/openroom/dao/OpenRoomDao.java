package com.kakaotalk.gift.domain.openroom.dao;

import com.kakaotalk.gift.domain.openroom.domain.OpenRoom;
import com.kakaotalk.gift.global.exception.BusinessException;
import com.kakaotalk.gift.global.exception.ErrorCode;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.kakaotalk.gift.domain.openroom.domain.QOpenRoom.openRoom;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OpenRoomDao {

    private final JPAQueryFactory queryFactory;

    public OpenRoom findByCode(String participationCode) {
        OpenRoom result = queryFactory
                .selectFrom(openRoom)
                .where(
                        eqParticipationCode(participationCode)
                )
                .fetchFirst();

        return Optional.ofNullable(result)
                .orElseThrow(() -> new BusinessException(ErrorCode.OPEN_ROOM_NOT_FOUND_EXCEPTION));
    }

    private BooleanExpression eqParticipationCode(String participationCode) {
        if (participationCode == null) return null;
        return openRoom.participationCode.participationCode.eq(participationCode);
    }
}
