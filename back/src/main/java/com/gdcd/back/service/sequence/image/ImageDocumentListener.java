package com.gdcd.back.service.sequence.image;

import com.gdcd.back.domain.image.Image;
import com.gdcd.back.domain.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ImageDocumentListener extends AbstractMongoEventListener<Image> {
    private final ImageSequenceGeneratorService imageSequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Image> event){
        event.getSource().set_id(imageSequenceGeneratorService.generateSequence(Image.IMAGE_SEQUENCE_NAME));
    }

}
