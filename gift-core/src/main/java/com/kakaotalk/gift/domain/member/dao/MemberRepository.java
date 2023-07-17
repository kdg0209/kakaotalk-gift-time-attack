package com.kakaotalk.gift.domain.member.dao;

import com.kakaotalk.gift.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
