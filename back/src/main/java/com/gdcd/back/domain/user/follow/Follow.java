package com.gdcd.back.domain.user.follow;

import com.gdcd.back.domain.user.User;
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
@Document(collection = "follows")
public class Follow {
    @Transient
    public static final String FOLLOW_SEQUENCE_NAME = "follow_sequences";
    @Id
    @Field(name = "_id")
    private Long id;
    private User follower;
    private User following;
}
