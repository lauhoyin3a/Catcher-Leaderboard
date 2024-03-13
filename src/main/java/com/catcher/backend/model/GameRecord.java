package com.catcher.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "records")
public class GameRecord {
    @Transient
    public static final String SEQUENCE_NAME = "record_sequence";

    @Id
    private int id;
    private int score;
    private String userName;
}
