package com.revature.backend.likes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends CrudRepository<Like, Integer> {

	Optional<Like> findByUserIdAndPostId(Integer claimedUserId, Integer postId);
}
