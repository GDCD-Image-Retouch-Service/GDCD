package com.gdcd.back.dto.post.response;

import com.gdcd.back.domain.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentUpperResponseDto {
    private Long commentId = 1L;
    private String content = "my name is my name";
    private String writerNickname;
    private String writerProfile;
    private LocalDateTime updateTime = LocalDateTime.now();
    private List<CommentKidResponseDto> kids;

    public CommentUpperResponseDto(Comment document, List<CommentKidResponseDto> kids) {
        this.commentId = document.getId();
        this.content = document.getContent();
        this.writerNickname = document.getWriter().getNickname();
        this.writerProfile = document.getWriter().getProfile();
        this.updateTime = LocalDateTime.now();
        this.kids = kids;
    }
}
