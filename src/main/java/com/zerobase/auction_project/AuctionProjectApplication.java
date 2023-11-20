package com.zerobase.auction_project;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableFeignClients
@SpringBootApplication
@EnableJpaAuditing
public class AuctionProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuctionProjectApplication.class, args);

    }

}
