package com.kakaotalk.gift.domain.openroom.domain;

import com.kakaotalk.gift.domain.member.domain.Member;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "open_room")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OpenRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Comment(value = "오픈 채팅방 참여코드")
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Comment(value = "생성일")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Comment(value = "유저의 아이디")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx", foreignKey = @ForeignKey(name = "fk_open_room_member"))
    private Member member;
}
