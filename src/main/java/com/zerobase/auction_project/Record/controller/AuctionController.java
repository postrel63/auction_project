package com.zerobase.auction_project.Record.controller;

import com.zerobase.auction_project.auction.domain.Auction;
import com.zerobase.auction_project.auction.domain.AuctionDto;
import com.zerobase.auction_project.auction.domain.AddAuctionForm;
import com.zerobase.auction_project.auction.domain.UpdateAuctionForm;
import com.zerobase.auction_project.jwt.config.JwtAuthenticationProvider;
import com.zerobase.auction_project.auction.service.AuctionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auctions")
public class AuctionController {

    private final JwtAuthenticationProvider provider;
    private final AuctionService auctionService;

    //등록
    @PostMapping
    public ResponseEntity<Auction> enrollAuction(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                                 @RequestBody AddAuctionForm form) {
        return ResponseEntity.ok(auctionService.enrollAuction(provider.getUserVo(token).getId(), form));
    }

    @PatchMapping
    public ResponseEntity<Auction> updateAuction(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                                 @RequestBody UpdateAuctionForm form) {
        return ResponseEntity.ok(auctionService.updateAuction(provider.getUserVo(token).getId(), form));
    }

    @DeleteMapping
    public String deleteAuction(@RequestHeader(name = "X-AUTH-TOKEN") String token, @RequestBody Long auctionId) {

        return auctionService.deleteAuction(provider.getUserVo(token).getId(), auctionId);
    }

    @GetMapping("/{auctionId}")
    public ResponseEntity<Auction> getAuction(@PathVariable Long auctionId,
                                              @RequestHeader(name = "X-AUTH-TOKEN") String token) {
        return ResponseEntity.ok(auctionService.getAuction(auctionId));
    }

    @GetMapping("/all")
    public List<AuctionDto> getAllAuction(@RequestHeader(name = "X-AUTH-TOKEN") String token) {
        return auctionService.getAllAuctions();
    }

}
