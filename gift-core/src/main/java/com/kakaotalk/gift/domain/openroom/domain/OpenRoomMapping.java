package com.kakaotalk.gift.domain.openroom.domain;

import com.kakaotalk.gift.domain.member.domain.Member;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "open_room_mapping")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OpenRoomMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Comment(value = "유저의 아이디")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx", foreignKey = @ForeignKey(name = "fk_open_room_mapping_member"))
    private Member member;

    @Comment(value = "오픈채팅방 아이디")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "open_room_idx", foreignKey = @ForeignKey(name = "fk_open_room_mapping_openRoom"))
    private OpenRoom openRoom;

    @Comment(value = "참여일")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public OpenRoomMapping(Member member, OpenRoom openRoom) {
        this.member = member;
        this.openRoom = openRoom;
        this.createdAt = LocalDateTime.now();
    }
}
