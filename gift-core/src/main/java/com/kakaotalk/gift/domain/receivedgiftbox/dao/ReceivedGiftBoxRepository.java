package com.kakaotalk.gift.domain.receivedgiftbox.dao;

import com.kakaotalk.gift.domain.receivedgiftbox.domain.ReceivedGiftBox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceivedGiftBoxRepository extends JpaRepository<ReceivedGiftBox, Long> {
}
