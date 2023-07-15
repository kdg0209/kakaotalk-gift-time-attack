package com.kakaotalk.gift.domain.sendgiftbox.domian;

import com.kakaotalk.gift.domain.member.domain.Member;
import com.kakaotalk.gift.domain.receivedgiftbox.domain.ReceivedGiftBox;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "send_gift_box")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SendGiftBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Comment(value = "선물의 총 수량")
    @Column(name = "gift_quantity", nullable = false)
    private Integer giftQuantity;

    @Comment(value = "사용가능한 수량")
    @Column(name = "available_quantity", nullable = false)
    private Integer availableQuantity;

    @Comment(value = "생성일")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "sendGiftBox")
    private ReceivedGiftBox receivedGiftBox;

    @Comment(value = "유저의 아이디")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx", foreignKey = @ForeignKey(name = "fk_send_gift_box_member"))
    private Member member;
}
