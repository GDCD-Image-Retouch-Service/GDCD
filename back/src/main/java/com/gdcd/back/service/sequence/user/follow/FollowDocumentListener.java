package com.gdcd.back.service.sequence.user.follow;

import com.gdcd.back.domain.user.block.Block;
import com.gdcd.back.domain.user.follow.Follow;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class FollowDocumentListener extends AbstractMongoEventListener<Follow> {
    private final FollowSequenceGeneratorService followSequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Follow> event){
        if (event.getSource().getId() != null) return;
        event.getSource().setId(followSequenceGeneratorService.generateSequence(Follow.FOLLOW_SEQUENCE_NAME));
    }
}
