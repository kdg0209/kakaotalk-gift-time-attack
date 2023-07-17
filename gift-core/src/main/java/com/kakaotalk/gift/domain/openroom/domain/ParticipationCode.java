package com.kakaotalk.gift.domain.openroom.domain;

import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 오픈 채팅방 참여 코드는 중복을 방지하기 위해 년월일시분과 랜덤 문자열을 포함하여 18자리로 구성
 * 2307172101 + 랜덤 문자열
 */
@Embeddable
public class ParticipationCode {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyMMddHHmm");

    private static final int CERT_CHAR_LENGTH = 8;
    private static final char[] CHARACTER_TABLE = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
    };
    private static final int CHARACTER_TABLE_LENGTH = CHARACTER_TABLE.length;


    @Comment(value = "오픈 채팅방 참여코드")
    @Column(name = "participation_code", nullable = false, unique = true)
    private String participationCode;

    public ParticipationCode() {
        this.participationCode = generateCode();
    }

    public String code() {
        return this.participationCode;
    }

    private String generateCode() {
        LocalDateTime now = LocalDateTime.now();
        String format = now.format(DATE_TIME_FORMATTER);

        StringBuilder sb = new StringBuilder();
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < CERT_CHAR_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTER_TABLE_LENGTH);
            Character randomText = CHARACTER_TABLE[randomIndex];
            sb.append(randomText);
        }
        return format + sb;
    }
}
