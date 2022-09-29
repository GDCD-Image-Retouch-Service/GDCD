package com.gdcd.back.domain.user.block;

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
@Document(collection = "blocks")
public class Block {
    @Transient
    public static final String BLOCK_SEQUENCE_NAME = "block_sequences";
    @Id
    @Field(name = "_id")
    private Long id;
    private User blocker;
    private User blocking;

    public void modifyBlocker(User blocker) {
        this.blocker = blocker;
    }

    public void modifyBlocking(User blocking) {
        this.blocking = blocking;
    }
}
