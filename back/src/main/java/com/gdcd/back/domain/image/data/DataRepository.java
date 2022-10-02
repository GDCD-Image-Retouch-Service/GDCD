package com.gdcd.back.domain.image.data;

import com.gdcd.back.domain.image.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataRepository extends MongoRepository<Data, Long> {
}
