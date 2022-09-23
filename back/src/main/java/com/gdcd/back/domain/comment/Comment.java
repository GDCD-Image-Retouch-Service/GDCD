package com.gdcd.back.domain.comment;

import com.gdcd.back.domain.user.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comments")
public class Comment {
    @Transient
    public static final String COMMENT_SEQUENCE_NAME = "comment_sequences";
    @Id
    @Field(name="_id")
    private Long id;
    private User writer;
    private Long postId;
    private String content;
    private Long upper;
    private LocalDateTime registDate;
    private LocalDateTime updateDate;
    private boolean validation;
}
