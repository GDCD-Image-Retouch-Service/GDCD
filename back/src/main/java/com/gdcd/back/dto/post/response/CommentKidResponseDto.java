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
    private Long commentId = 2L;
    private String content = "your name is your name";
    private String writerNickname;
    private String writerProfile;
    private LocalDateTime updateTime = LocalDateTime.now();

    public CommentKidResponseDto(Comment document) {
        this.commentId = document.getId();
        this.content = document.getContent();
        this.writerNickname = document.getWriter().getNickname();
        this.writerProfile = document.getWriter().getProfile();
        this.updateTime = LocalDateTime.now();
    }
}
