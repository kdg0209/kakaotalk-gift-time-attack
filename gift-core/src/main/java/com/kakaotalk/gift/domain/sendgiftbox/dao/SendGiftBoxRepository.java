package com.kakaotalk.gift.domain.sendgiftbox.dao;

import com.kakaotalk.gift.domain.sendgiftbox.domian.SendGiftBox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SendGiftBoxRepository extends JpaRepository<SendGiftBox, Long> {
}
