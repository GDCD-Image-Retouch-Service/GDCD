package com.gdcd.back.dto.post.request;

import com.gdcd.back.domain.post.report.Report;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostReportRequestDto {
    private Long postId;
    private String content;

    public Report toDocument(Long userId){
       return Report.builder()
               .postId(postId)
               .content(content)
               .userId(userId)
               .build();
    }
}
