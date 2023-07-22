package com.kakaotalk.gift.domain.receivedgiftbox.domain;

import com.kakaotalk.gift.domain.sendgiftbox.domian.SendGiftBox;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "received_gift_box")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReceivedGiftBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Comment(value = "유저의 아이디")
    @Column(name = "member_id", nullable = false, updatable = false)
    private String memberId;

    @Comment(value = "생성일")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Comment(value = "보낸 선물함의 PK")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "send_gift_box_idx", foreignKey = @ForeignKey(name = "fk_received_gift_box_send_gift_box"))
    private SendGiftBox sendGiftBox;

    public ReceivedGiftBox(String memberId, SendGiftBox sendGiftBox) {
        this.memberId = memberId;
        this.sendGiftBox = sendGiftBox;
        this.createdAt = LocalDateTime.now();
    }
}
