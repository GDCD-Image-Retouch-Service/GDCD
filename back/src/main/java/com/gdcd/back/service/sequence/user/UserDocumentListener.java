package com.gdcd.back.service.sequence.user;

import com.gdcd.back.domain.post.Post;
import com.gdcd.back.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserDocumentListener extends AbstractMongoEventListener<User> {
    private final UserSequenceGeneratorService userSequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<User> event){
        if (event.getSource().getId() != null) return;
        event.getSource().setId(userSequenceGeneratorService.generateSequence(User.USER_SEQUENCE_NAME));
    }

}
