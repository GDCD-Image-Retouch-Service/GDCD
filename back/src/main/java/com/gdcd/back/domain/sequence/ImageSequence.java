package com.gdcd.back.domain.sequence;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "image_sequences")
public class ImageSequence {
    @Id
    private String id;
    private Long seq;
}
