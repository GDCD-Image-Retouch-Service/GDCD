package com.gdcd.back.domain.sequence;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "report_sequences")
public class ReportSequence {
    @Id
    private String id;
    private Long seq;
}