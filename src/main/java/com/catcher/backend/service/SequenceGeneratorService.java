package com.catcher.backend.service;

import com.catcher.backend.model.DbSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class SequenceGeneratorService {
    @Autowired
    private MongoOperations mongoOperations;

    // Get the next sequence number for the given sequence name
    public int getSequenceNumber(String sequenceName) {
        // Create a query to find the document with the given sequence name
        Query query = new Query(Criteria.where("id").is(sequenceName));
        // Create an update to increment the sequence number by 1
        Update update = new Update().inc("seq", 1);
        // Find and modify the document, returning the modified document
        DbSequence counter = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true), DbSequence.class);

        if (Objects.isNull(counter)) {
            return 1;
        } else {
            return counter.getSeq();
        }

    }

}
