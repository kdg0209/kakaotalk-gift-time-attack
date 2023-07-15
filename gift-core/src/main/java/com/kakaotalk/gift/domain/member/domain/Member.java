package com.kakaotalk.gift.domain.member.domain;

import com.kakaotalk.gift.domain.openroom.domain.OpenRoom;
import com.kakaotalk.gift.domain.receivedgiftbox.domain.ReceivedGiftBox;
import com.kakaotalk.gift.domain.sendgiftbox.domian.SendGiftBox;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Comment(value = "아이디")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Comment(value = "생성일")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<OpenRoom> openRooms = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<SendGiftBox> sendGiftBoxes = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<ReceivedGiftBox> receivedGiftBoxes = new ArrayList<>();
}
