package com.kakaotalk.gift.domain.sendgiftbox.domian;

import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

@Embeddable
public class GiftSerialCode {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyMMddHHmm");

    private static final int CERT_CHAR_LENGTH = 8;
    private static final char[] CHARACTER_TABLE = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
    };
    private static final int CHARACTER_TABLE_LENGTH = CHARACTER_TABLE.length;

    @Comment(value = "선물 시리얼 코드")
    @Column(name = "gift_serial_code", nullable = false, unique = true)
    private String giftSerialCode;

    public GiftSerialCode() {
        this.giftSerialCode = generate() + ":" + generate();
    }

    public String getGiftSerialCode() {
        return this.giftSerialCode;
    }

    private static String generate() {
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
