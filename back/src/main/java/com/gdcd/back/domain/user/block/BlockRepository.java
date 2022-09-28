package com.gdcd.back.domain.user.block;

import com.gdcd.back.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BlockRepository extends MongoRepository<Block, Long> {
    Optional<Block> findByBlockerAndBlocking(User blocker, User blocking);
    void deleteByBlockerAndBlocking(User blocker, User blocking);
    List<Block> findAllByBlocker(User blocker);
    List<Block> findAllByBlocking(User blocking);
}
