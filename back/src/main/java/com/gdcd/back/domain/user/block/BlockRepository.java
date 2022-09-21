package com.gdcd.back.domain.user.block;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlockRepository extends MongoRepository<Block, Long> {
}
