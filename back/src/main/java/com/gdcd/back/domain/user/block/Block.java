package com.gdcd.back.domain.user.block;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "blocks")
public class Block {
    // sequence
    @Id
    @Field(name = "_id")
    private Long id;
    private Long blocker;
    private Long blocking;
    @Field(name = "blocking_nickname")
    private Long blockingNickname;
    @Field(name = "blocking_profile")
    private Long blockingProfile;
}
