package com.zerobase.auction_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Record extends JpaRepository<Record,String> {
}
