package com.gdcd.back.domain.comment;

import com.gdcd.back.domain.user.UserSimple;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, Long> {
    List<Comment> findAllByWriter(UserSimple writer);

    List<Comment> findAllByWriterOrderByRegistDateDesc(UserSimple writer);
    List<Comment> findAllByPostIdAndUpperAndValidation(Long postId, Long upper, boolean validation);
    List<Comment> findAllByPostIdAndUpperAndValidationOrderByRegistDateDesc(Long postId, Long upper, boolean validation, Pageable pageable);

    List<Comment> findAllByUpperAndValidation(Long upper, boolean validation);
    List<Comment> findAllByUpperAndValidationOrderByRegistDateDesc(Long upper, boolean validation, Pageable pageable);

}
