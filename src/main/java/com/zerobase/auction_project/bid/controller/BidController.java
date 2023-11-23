package com.zerobase.auction_project.bid.controller;

import com.zerobase.auction_project.jwt.config.JwtAuthenticationProvider;
import com.zerobase.auction_project.bid.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bids")
public class BidController {

    private final BidService bidService;
    private final JwtAuthenticationProvider provider;


}
