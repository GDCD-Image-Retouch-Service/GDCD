package com.gdcd.back.domain.image;

import com.gdcd.back.domain.post.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, String> {
}
