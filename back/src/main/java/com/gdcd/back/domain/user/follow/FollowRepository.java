package com.gdcd.back.domain.user.follow;

import com.gdcd.back.domain.user.UserSimple;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends MongoRepository<Follow, Long> {
    Optional<Follow> findByFollowerAndFollowing(UserSimple follower, UserSimple following);
    void deleteByFollowerAndFollowing(UserSimple follower, UserSimple following);
    List<Follow> findAllByFollower(UserSimple follower);
    List<Follow> findAllByFollowing(UserSimple following);
}
