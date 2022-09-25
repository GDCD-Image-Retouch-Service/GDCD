package com.gdcd.back.service.sequence.post.report;

import com.gdcd.back.domain.post.Post;
import com.gdcd.back.domain.post.report.Report;
import com.gdcd.back.service.sequence.post.PostSequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportDocumentListener extends AbstractMongoEventListener<Report> {

    private final ReportSequenceGeneratorService reportSequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Report> event){
        if (event.getSource().getId() != null) return;
        event.getSource().setId(reportSequenceGeneratorService.generateSequence(Report.REPORT_SEQUENCE_NAME));
    }
}
