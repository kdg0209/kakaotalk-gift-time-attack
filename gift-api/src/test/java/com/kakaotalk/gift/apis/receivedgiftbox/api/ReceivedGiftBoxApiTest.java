package com.kakaotalk.gift.apis.receivedgiftbox.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaotalk.gift.apis.receivedgiftbox.dto.ReceivedGiftBoxCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class ReceivedGiftBoxApiTest {

    private static final ExecutorService executor = Executors.newFixedThreadPool(30);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    void 동시에_1000명이_요청하는_테소트() throws InterruptedException {
        int threadCount = 1000;
        CountDownLatch latch = new CountDownLatch(threadCount);
        String giftSerialCode = "2307262320C3PT3LGE:23072623205CI9WVH9";

        for (int i = 0; i < threadCount; i++) {
            CompletableFuture.runAsync(() -> {
                try {
                    ReceivedGiftBoxCreateRequest request = new ReceivedGiftBoxCreateRequest(String.valueOf(System.currentTimeMillis()), "2307262314ET0BTGBA", giftSerialCode);
                    String requestBody = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(request);
                    HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(requestBody);

                    HttpRequest httpRequest = HttpRequest.newBuilder()
                            .uri(URI.create("http://localhost:8080/received-gift-box"))
                            .POST(body)
                            .header("Content-Type", "application/json")
                            .build();

                    HttpClient client = HttpClient.newBuilder().build();
                    client.send(httpRequest, HttpResponse.BodyHandlers.discarding());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    latch.countDown();
                }
            }, executor);
        }

        latch.await();
    }

}