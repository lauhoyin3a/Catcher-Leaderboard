package com.catcher.backend.controller;

import com.catcher.backend.model.GameRecord;
import com.catcher.backend.repository.GameRecordRepository;
import com.catcher.backend.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/gamerecord")
public class GameRecordController {
    @Autowired
    private GameRecordRepository repository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @PostMapping
    public GameRecord saveGameRecord(@RequestBody GameRecord gameRecord){
        gameRecord.setId(sequenceGeneratorService.getSequenceNumber(GameRecord.SEQUENCE_NAME));

        return repository.save(gameRecord);
    }
    @GetMapping
    public List<GameRecord> getGameRecords(){
        return repository.findAll();
    }

}
