package com.catcher.backend.service;

import com.catcher.backend.model.GameRecord;
import com.catcher.backend.repository.GameRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameRecordService {
    private final GameRecordRepository repository;
    private final SequenceGeneratorService sequenceGeneratorService;

    public GameRecordService(GameRecordRepository repository, SequenceGeneratorService sequenceGeneratorService) {
        this.repository = repository;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    // save a new game record
    public GameRecord saveGameRecord(GameRecord gameRecord) {
        String userName = gameRecord.getUserName();
        if (repository.existsByUserName(userName)) {
            throw new IllegalArgumentException("User Name Exists");
        }
        gameRecord.setId(sequenceGeneratorService.getSequenceNumber(GameRecord.SEQUENCE_NAME));
        return repository.save(gameRecord);
    }

    // Get all game records
    public List<GameRecord> getGameRecords() {
        return repository.findAll();
    }

    // Get the rank of a user based on their username
    public int getUserRank(String userName) {
        List<GameRecord> gameRecords = repository.findAllByOrderByScoreDesc();
        int rank = 0;
        for (GameRecord gameRecord : gameRecords) {
            rank++;
            //System.out.println(gameRecord.getUserName());
            if (gameRecord.getUserName().equals(userName)) {
                return rank;
            }
        }
        return -1;
    }

    // Get the top 100 highest scores
    public List<GameRecord> getTop100Score() {
        return repository.findTop100ByOrderByScoreDesc();
    }

}