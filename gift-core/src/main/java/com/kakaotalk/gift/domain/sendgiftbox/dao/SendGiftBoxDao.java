package com.kakaotalk.gift.domain.sendgiftbox.dao;

import com.kakaotalk.gift.domain.sendgiftbox.domian.SendGiftBox;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.kakaotalk.gift.domain.sendgiftbox.domian.QSendGiftBox.sendGiftBox;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SendGiftBoxDao {

    private final JPAQueryFactory queryFactory;

    public Optional<SendGiftBox> findByGiftSerialCode(String giftSerialCode) {
        SendGiftBox result = queryFactory
                .selectFrom(sendGiftBox)
                .where(
                        eqGiftSerialCode(giftSerialCode)
                )
                .fetchFirst();

        return Optional.ofNullable(result);
    }

    private BooleanExpression eqGiftSerialCode(String giftSerialCode) {
        if (giftSerialCode == null) return null;
        return sendGiftBox.giftSerialCode.giftSerialCode.eq(giftSerialCode);
    }
}
