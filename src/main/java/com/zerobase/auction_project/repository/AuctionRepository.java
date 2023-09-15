package com.zerobase.auction_project.repository;

import com.zerobase.auction_project.domain.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, String> {

    Optional<Auction> findBySellerIdAndId(Long userid, Long id);

    Optional<Auction> findById(Long auctionId);



}
