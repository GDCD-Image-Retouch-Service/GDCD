package com.gdcd.back.domain.image;

import com.gdcd.back.domain.post.Post;
import com.gdcd.back.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends MongoRepository<Image, Long> {
    List<Image> findAllByUserId(Long userId);
    List<Image> findAllByUserIdAndBeforeImage(Long userId, Boolean before);

    Optional<Image> findByFilePath(String path);

}
