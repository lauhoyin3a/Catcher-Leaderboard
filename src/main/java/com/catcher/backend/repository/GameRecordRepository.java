package com.catcher.backend.repository;

import com.catcher.backend.model.GameRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRecordRepository extends MongoRepository<GameRecord, Integer> {

}
