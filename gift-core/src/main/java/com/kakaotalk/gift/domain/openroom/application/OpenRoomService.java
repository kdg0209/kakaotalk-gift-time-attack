package com.kakaotalk.gift.domain.openroom.application;

import com.kakaotalk.gift.domain.member.dao.MemberDao;
import com.kakaotalk.gift.domain.member.domain.Member;
import com.kakaotalk.gift.domain.openroom.dao.OpenRoomDao;
import com.kakaotalk.gift.domain.openroom.dao.OpenRoomMappingDao;
import com.kakaotalk.gift.domain.openroom.dao.OpenRoomMappingRepository;
import com.kakaotalk.gift.domain.openroom.dao.OpenRoomRepository;
import com.kakaotalk.gift.domain.openroom.domain.OpenRoom;
import com.kakaotalk.gift.domain.openroom.domain.OpenRoomMapping;
import com.kakaotalk.gift.global.exception.BusinessException;
import com.kakaotalk.gift.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OpenRoomService {

    private final MemberDao memberDao;
    private final OpenRoomDao openRoomDao;
    private final OpenRoomMappingDao openRoomMappingDao;
    private final OpenRoomRepository openRoomRepository;
    private final OpenRoomMappingRepository openRoomMappingRepository;

    public String create(Long memberIdx) {
        Member member = memberDao.findByIdx(memberIdx);

        OpenRoom openRoom = new OpenRoom(member);
        OpenRoomMapping openRoomMapping = new OpenRoomMapping(member, openRoom);

        openRoomRepository.save(openRoom);
        openRoomMappingRepository.save(openRoomMapping);
        return openRoom.code();
    }

    public Long join(Long memberIdx, String participationCode) {
        Member member = memberDao.findByIdx(memberIdx);
        OpenRoom openRoom = openRoomDao.findByCode(participationCode);
        boolean roomAlreadyJoined = openRoomMappingDao.isRoomAlreadyJoined(openRoom.getIdx(), memberIdx);

        if (roomAlreadyJoined) {
            throw new BusinessException(ErrorCode.OPEN_ROOM_ALREADY_JOINED_EXCEPTION);
        }

        OpenRoomMapping openRoomMapping = new OpenRoomMapping(member, openRoom);
        openRoomMappingRepository.save(openRoomMapping);
        return openRoom.getIdx();
    }
}
