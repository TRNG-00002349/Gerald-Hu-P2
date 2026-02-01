package com.revature.backend.likes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends CrudRepository<Like, Integer> {

	void deleteByUserIdAndPostId(Integer userId, Integer postId);
}
