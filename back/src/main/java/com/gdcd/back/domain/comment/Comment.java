package com.gdcd.back.domain.comment;

import com.gdcd.back.domain.user.User;
import com.gdcd.back.domain.user.UserSimple;
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
    private UserSimple writer;
    @Field(name = "post_id")
    private Long postId;
    private String content;
    private Long upper;
    @Field(name = "regist_date")
    private LocalDateTime registDate;
    @Field(name = "update_date")
    private LocalDateTime updateDate;
    private boolean validation;
}
