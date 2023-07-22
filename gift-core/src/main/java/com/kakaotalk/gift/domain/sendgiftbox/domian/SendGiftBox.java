package com.kakaotalk.gift.domain.sendgiftbox.domian;

import com.kakaotalk.gift.domain.member.domain.Member;
import com.kakaotalk.gift.domain.receivedgiftbox.domain.ReceivedGiftBox;
import com.kakaotalk.gift.global.exception.CustomIllegalArgumentException;
import com.kakaotalk.gift.global.exception.ErrorCode;
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

    @Comment(value = "오픈 채팅방의 코드")
    @Column(name = "open_room_code", nullable = false, updatable = false)
    private String openRoomCode;

    @Embedded
    private GiftSerialCode giftSerialCode;

    @Comment(value = "선물의 이름")
    @Column(name = "gift_name", nullable = false)
    private String giftName;

    @Comment(value = "선물의 총 수량")
    @Column(name = "gift_quantity", nullable = false)
    private int giftQuantity;

    @Comment(value = "사용가능한 수량")
    @Column(name = "available_quantity", nullable = false)
    private int availableQuantity;

    @Comment(value = "생성일")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "sendGiftBox")
    private ReceivedGiftBox receivedGiftBox;

    @Comment(value = "선물한 유저의 아이디")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx", foreignKey = @ForeignKey(name = "fk_send_gift_box_member"))
    private Member member;

    public SendGiftBox(Member member, String openRoomCode, String giftName, int giftQuantity) {
        validatorMember(member);
        validatorOpenRoomCode(openRoomCode);
        this.member = member;
        this.openRoomCode = openRoomCode;
        this.giftSerialCode = new GiftSerialCode();
        this.giftName = giftName;
        this.giftQuantity = giftQuantity;
        this.availableQuantity = giftQuantity;
        this.createdAt = LocalDateTime.now();
    }

    public String getGiftSerialCode() {
        return this.giftSerialCode.getGiftSerialCode();
    }

    private void validatorMember(Member member) {
        if (member == null) {
            throw new CustomIllegalArgumentException(ErrorCode.ILLEGAL_ARGUMENT_EXCEPTION, new String[]{"사용자 정보를 찾을 수 없습니다."});
        }
    }

    private void validatorOpenRoomCode(String openRoomCode) {
        if (openRoomCode == null) {
            throw new CustomIllegalArgumentException(ErrorCode.ILLEGAL_ARGUMENT_EXCEPTION, new String[]{"오픈채팅방 정보를 찾을 수 없습니다."});
        }
    }
}
