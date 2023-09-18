package com.zerobase.auction_project;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan
@EnableFeignClients
@SpringBootApplication
@RequiredArgsConstructor
@EnableJpaAuditing
@EnableJpaRepositories
public class AuctionProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuctionProjectApplication.class, args);

    }

}
