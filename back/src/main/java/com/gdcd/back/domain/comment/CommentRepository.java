package com.gdcd.back.domain.comment;

import com.gdcd.back.domain.user.UserSimple;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, Long> {
    List<Comment> findAllByWriter(UserSimple writer);
    List<Comment> findAllByPostIdAndUpper(Long postId, Long upper);
    List<Comment> findAllByUpper(Long upper);
}
