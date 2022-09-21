//package com.gdcd.back.service.sequence.comment;
//
//import com.gdcd.back.domain.comment.Comment;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
//import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
//import org.springframework.stereotype.Component;
//
//@RequiredArgsConstructor
//@Component
//public class CommentDocumentListener extends AbstractMongoEventListener<Comment> {
//    private final CommentSequenceGeneratorService commentSequenceGeneratorService;
//
//    @Override
//    public void onBeforeConvert(BeforeConvertEvent<Comment> event){ //추후에 Comment로 변경해주세요
//        event.getSource().set_id(commentSequenceGeneratorService.generateSequence(Comment.COMMENT_SEQUENCE_NAME));
//    }

//}
