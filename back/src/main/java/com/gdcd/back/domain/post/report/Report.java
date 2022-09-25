package com.gdcd.back.domain.post.report;


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
@Document(collection = "reports")
public class Report {
    @Transient
    public static final String REPORT_SEQUENCE_NAME = "report_sequences";

    @Id
    @Field(name = "_id")
    private Long id;
    @Field(name = "user_id")
    private Long userId;
    @Field(name = "post_id")
    private Long postId;
    private String content;
}
