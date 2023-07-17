package com.kakaotalk.gift.domain.openroom.application;

import com.kakaotalk.gift.domain.member.dao.MemberDao;
import com.kakaotalk.gift.domain.member.domain.Member;
import com.kakaotalk.gift.domain.openroom.dao.OpenRoomMappingRepository;
import com.kakaotalk.gift.domain.openroom.dao.OpenRoomRepository;
import com.kakaotalk.gift.domain.openroom.domain.OpenRoom;
import com.kakaotalk.gift.domain.openroom.domain.OpenRoomMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OpenRoomService {

    private final MemberDao memberDao;
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
}
