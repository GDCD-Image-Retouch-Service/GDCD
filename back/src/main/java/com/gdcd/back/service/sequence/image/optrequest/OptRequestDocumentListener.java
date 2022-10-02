package com.gdcd.back.service.sequence.image.optrequest;

import com.gdcd.back.domain.image.Image;
import com.gdcd.back.domain.image.optrequest.OptRequest;
import com.gdcd.back.service.sequence.image.ImageSequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OptRequestDocumentListener extends AbstractMongoEventListener<OptRequest> {
    private final OptRequestSequenceGeneratorService optRequestSequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<OptRequest> event){
        if (event.getSource().getId() != null) return;
        event.getSource().setId(optRequestSequenceGeneratorService.generateSequence(OptRequest.OREQUEST_SEQUENCE_NAME));
    }

}
