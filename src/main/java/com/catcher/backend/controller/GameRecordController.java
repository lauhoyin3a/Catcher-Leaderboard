package com.catcher.backend.controller;

import com.catcher.backend.model.GameRecord;
import com.catcher.backend.service.GameRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/game_record")
public class GameRecordController {
    @Autowired
    private final GameRecordService gameRecordService;

    public GameRecordController(GameRecordService gameRecordService) {
        this.gameRecordService = gameRecordService;
    }

    // Create a new game record with username and score
    @PostMapping
    public ResponseEntity<String> createGameRecord(@RequestBody GameRecord gameRecord) {
        try {
            GameRecord savedGameRecord = gameRecordService.saveGameRecord(gameRecord);
            return ResponseEntity.status(HttpStatus.CREATED).body("Game record created successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // Get all game records
    @GetMapping
    public List<GameRecord> getGameRecords() {
        return gameRecordService.getGameRecords();
    }

    // Get the rank of a user based on their username
    @GetMapping("/user_rank")
    public int getUserRank(@RequestParam("username") String userName) {
        return gameRecordService.getUserRank(userName);
    }

    // Get the top 100 highest scores
    @GetMapping("/top_scores")
    public List<GameRecord> getTop100Score() {
        return gameRecordService.getTop100Score();
    }
}