package com.zerobase.auction_project.repository;

import com.zerobase.auction_project.domain.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid,String> {
}
