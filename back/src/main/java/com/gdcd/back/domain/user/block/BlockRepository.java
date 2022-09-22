package com.gdcd.back.domain.user.block;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BlockRepository extends MongoRepository<Block, Long> {
    Optional<Block> findByBlockerAndBlocking(String blocker, String blocking);
}
