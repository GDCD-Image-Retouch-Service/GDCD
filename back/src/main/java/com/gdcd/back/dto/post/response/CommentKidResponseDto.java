package com.gdcd.back.dto.post.response;

import com.gdcd.back.domain.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentKidResponseDto {
    private Long commentId;
    private String content;
    private String writerNickname;
    private String writerProfile;
    private LocalDateTime updateTime;

    public CommentKidResponseDto(Comment comment) {
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.writerNickname = comment.getWriter().getNickname();
        this.writerProfile = comment.getWriter().getProfile();
        this.updateTime = comment.getUpdateDate();
    }
}
