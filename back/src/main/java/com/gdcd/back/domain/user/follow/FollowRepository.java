package com.gdcd.back.domain.user.follow;

import com.gdcd.back.domain.user.UserSimple;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends MongoRepository<Follow, Long> {
    Optional<Follow> findByFollowerAndFollowing(UserSimple follower, UserSimple following);
    void deleteByFollowerAndFollowing(UserSimple follower, UserSimple following);
    List<Follow> findAllByFollowerOrderById(UserSimple follower, Pageable pageable);
    List<Follow> findAllByFollowingOrderById(UserSimple following, Pageable pageable);
    List<Follow> findAllByFollower(UserSimple follower);
    List<Follow> findAllByFollowing(UserSimple following);
}
