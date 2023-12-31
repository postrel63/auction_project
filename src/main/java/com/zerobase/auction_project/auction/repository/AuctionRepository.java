package com.zerobase.auction_project.auction.repository;

import com.zerobase.auction_project.auction.domain.Auction;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, String> {

    Optional<Auction> findBySellerIdAndId(Long userid, Long id);

    @EntityGraph(value = "Auction.bids", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Auction> findById(Long auctionId);



}
