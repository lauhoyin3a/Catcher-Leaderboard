package com.catcher.backend.repository;

import com.catcher.backend.model.GameRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRecordRepository extends MongoRepository<GameRecord, Integer> {

    boolean existsByUserName(String userName);

    List<GameRecord> findAllByOrderByScoreDesc();

    List<GameRecord> findTop100ByOrderByScoreDesc();
}