package com.gdcd.back.domain.image.optrequest;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Builder
@Document(collection = "requests")
public class OptRequest {
    @Transient
    public static final String OREQUEST_SEQUENCE_NAME = "orequest_sequences";

    @Id
    @Field(name = "_id")
    private Long id;
    private Long user;
    private int done;

    public void update(int done) {
        this.done = done;
    }
}
