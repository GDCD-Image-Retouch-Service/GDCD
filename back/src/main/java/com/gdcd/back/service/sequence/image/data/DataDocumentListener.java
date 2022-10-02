package com.gdcd.back.service.sequence.image.data;

import com.gdcd.back.domain.image.Image;
import com.gdcd.back.domain.image.data.Data;
import com.gdcd.back.service.sequence.image.ImageSequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataDocumentListener extends AbstractMongoEventListener<Data> {
    private final DataSequenceGeneratorService dataSequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Data> event){
        if (event.getSource().getId() != null) return;
        event.getSource().setId(dataSequenceGeneratorService.generateSequence(Data.DATA_SEQUENCE_NAME));
    }
}
