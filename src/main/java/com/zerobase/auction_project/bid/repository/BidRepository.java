package com.zerobase.auction_project.bid.repository;

import com.zerobase.auction_project.bid.domain.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, String> {


    List<Bid> findByAuction_Id(Long auctionId);

    Boolean existsByAuction_Id(Long auctionId);

    Bid findFirstByAuction_IdOrderByPriceDesc(Long auctionId);

}
