package com.gdcd.back.domain.user.block;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "blocks")
public class Block {
    @Transient
    public static final String BLOCK_SEQUENCE_NAME = "block_sequences";
    @Id
    @Field(name = "_id")
    private Long id;
    private Long blocker;
    private Long blocking;
    @Field(name = "blocking_nickname")
    private String blockingNickname;
    @Field(name = "blocking_profile")
    private String blockingProfile;
}
