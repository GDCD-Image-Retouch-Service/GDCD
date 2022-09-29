package com.gdcd.back.domain.post;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, Long> {
    List<Post> findAllByWriterNo(Long userId);
}
