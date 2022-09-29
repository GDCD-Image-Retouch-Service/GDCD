package com.gdcd.back.domain.comment;

import com.gdcd.back.domain.user.UserSimple;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, Long> {
    List<Comment> findAllByWriter(UserSimple writer);
    List<Comment> findAllByPostIdAndUpperAndValidation(Long postId, Long upper, boolean validation);
    List<Comment> findAllByUpperAndValidation(Long upper, boolean validation);
}
