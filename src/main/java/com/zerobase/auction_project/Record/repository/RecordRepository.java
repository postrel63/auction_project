package com.zerobase.auction_project.Record.repository;

import com.zerobase.auction_project.Record.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, String> {
}
