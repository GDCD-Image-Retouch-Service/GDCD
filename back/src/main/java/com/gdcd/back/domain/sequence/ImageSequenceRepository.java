package com.gdcd.back.domain.sequence;

import com.gdcd.back.domain.image.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ImageSequenceRepository extends MongoRepository<ImageSequence, String> {
}
