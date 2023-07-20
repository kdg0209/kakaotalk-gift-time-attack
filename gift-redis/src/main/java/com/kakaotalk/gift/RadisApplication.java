package com.kakaotalk.gift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RadisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RadisApplication.class, args);
    }
}