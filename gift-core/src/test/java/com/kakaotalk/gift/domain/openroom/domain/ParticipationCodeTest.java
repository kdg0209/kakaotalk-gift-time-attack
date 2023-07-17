package com.kakaotalk.gift.domain.openroom.domain;


import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipationCodeTest {

    @Test
    void 길이가_18자리인_문자열_생성() {

        ParticipationCode participationCode = new ParticipationCode();

        assertThat(participationCode.code()).isNotNull();
        assertThat(participationCode.code().length()).isEqualTo(18);
    }

    @Test
    void 동시에_10000_명이_생성한_경우_동시성_테스트() throws InterruptedException {
        int threadCount = 10000;
        CountDownLatch latch = new CountDownLatch(threadCount);
        Set<CompletableFuture<String>> futures = new HashSet<>();

        for (int i = 0; i < threadCount; i++) {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                try {
                    ParticipationCode participationCode = new ParticipationCode();
                    return participationCode.code();
                } finally {
                    latch.countDown();
                }
            });
            futures.add(future);
        }
        latch.await();

        int result = futures.size();

        assertThat(result).isEqualTo(10000);
    }

}