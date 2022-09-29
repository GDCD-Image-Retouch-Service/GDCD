package com.gdcd.back.service.sequence.post;

import com.gdcd.back.domain.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostDocumentListener extends AbstractMongoEventListener<Post> {
    private final PostSequenceGeneratorService postSequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Post> event){
        if (event.getSource().getId() != null) return;
        event.getSource().setId(postSequenceGeneratorService.generateSequence(Post.POST_SEQUENCE_NAME));
    }

}
