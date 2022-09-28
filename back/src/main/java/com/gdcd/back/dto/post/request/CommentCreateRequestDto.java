package com.gdcd.back.dto.post.request;

import com.gdcd.back.domain.comment.Comment;
import com.gdcd.back.domain.user.UserSimple;
import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateRequestDto {
    private Long postId;
    private String content;
    @Nullable
    private Long upper;

    public Comment toDocument(UserSimple writer) {
        return Comment.builder()
                .writer(writer)
                .postId(postId)
                .content(content)
                .upper(upper == null? 0 : upper)
                .registDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .validation(true)
                .build();
    }
}
