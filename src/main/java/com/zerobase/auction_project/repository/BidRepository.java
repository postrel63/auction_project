package com.zerobase.auction_project.repository;

import com.zerobase.auction_project.domain.Bid;
import com.zerobase.auction_project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, String> {


    List<Bid> findByAuction_Id(Long auctionId);

    Boolean existsByAuction_Id(Long auctionId);

    Bid findFirstByAuction_IdOrderByPriceDesc (Long auctionId);

}
