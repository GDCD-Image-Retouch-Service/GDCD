package com.gdcd.back.domain.user.block;

import com.gdcd.back.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BlockRepository extends MongoRepository<Block, Long> {
    Optional<Block> findByBlockerAndBlocking(String blocker, User blocking);
    void deleteByBlockerAndBlocking(String blocker, User blocking);
}
