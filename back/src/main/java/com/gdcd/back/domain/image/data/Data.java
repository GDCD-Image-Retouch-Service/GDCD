package com.gdcd.back.domain.image.data;

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
@Document(collection = "data")
public class Data {
    @Transient
    public static final String DATA_SEQUENCE_NAME = "data_sequences";

    @Id
    @Field(name = "_id")
    private Long id;
    @Field(name = "image_id")
    private Long imageId;
    private int aesthetic;
    private int quality;
}
