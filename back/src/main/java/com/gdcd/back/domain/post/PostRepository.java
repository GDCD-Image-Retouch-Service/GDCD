package com.gdcd.back.domain.post;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, Long> {
    List<Post> findAllByWriterNo(Long userId);
    List<Post> findAllByWriterNoOrderByRegistTimeDesc(Long userId, Pageable pageable);

    List<Post> findAllByOrderByRegistTimeDesc(Pageable pageable);

}
