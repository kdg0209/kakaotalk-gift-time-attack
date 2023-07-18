package com.kakaotalk.gift.domain.sendgiftbox.application;

import com.kakaotalk.gift.domain.member.dao.MemberDao;
import com.kakaotalk.gift.domain.member.domain.Member;
import com.kakaotalk.gift.domain.openroom.dao.OpenRoomDao;
import com.kakaotalk.gift.domain.openroom.domain.OpenRoom;
import com.kakaotalk.gift.domain.sendgiftbox.dao.SendGiftBoxRepository;
import com.kakaotalk.gift.domain.sendgiftbox.domian.SendGiftBox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SendGiftBoxService {

    private final MemberDao memberDao;
    private final OpenRoomDao openRoomDao;
    private final SendGiftBoxRepository sendGiftBoxRepository;

    public Long create(Long memberIdx, String openRoomCode, String giftName, int giftQuantity) {
        Member member = memberDao.findByIdx(memberIdx);
        OpenRoom openRoom = openRoomDao.findByCode(openRoomCode);

        SendGiftBox sendGiftBox = new SendGiftBox(member, openRoom.code(), giftName, giftQuantity);
        sendGiftBoxRepository.save(sendGiftBox);

        return sendGiftBox.getIdx();
    }
}
