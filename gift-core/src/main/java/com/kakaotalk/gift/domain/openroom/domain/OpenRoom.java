package com.kakaotalk.gift.domain.openroom.domain;

import com.kakaotalk.gift.domain.member.domain.Member;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "open_room")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OpenRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Embedded
    private ParticipationCode participationCode;

    @Comment(value = "생성일")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Comment(value = "소유자 여부")
    @Column(name = "is_owner", nullable = false)
    private boolean isOwner;

    @Comment(value = "유저의 아이디")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx", foreignKey = @ForeignKey(name = "fk_open_room_member"))
    private Member member;

    @OneToMany(mappedBy = "openRoom", fetch = FetchType.LAZY)
    private List<OpenRoomMapping> openRoomMappings = new ArrayList<>();

    public OpenRoom(Member member) {
        this.participationCode = new ParticipationCode();
        this.createdAt = LocalDateTime.now();
        this.isOwner = true;
        this.member = member;
    }

    public String code() {
        return this.participationCode.code();
    }

    public Long getIdx() {
        return idx;
    }
}
