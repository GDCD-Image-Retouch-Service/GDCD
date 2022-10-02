package com.gdcd.back.service.sequence.image.data;

import com.gdcd.back.domain.sequence.DataSequence;
import com.gdcd.back.domain.sequence.ImageSequence;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@RequiredArgsConstructor
@Service
public class DataSequenceGeneratorService {
    private final MongoOperations mongoOperations;

    public long generateSequence(String seqName){
        DataSequence counter = mongoOperations.findAndModify(Query.query(where("_id").is(seqName)),
                new Update().inc("seq",1), FindAndModifyOptions.options().returnNew(true).upsert(true),
                DataSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
