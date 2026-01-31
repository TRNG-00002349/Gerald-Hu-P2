package com.revature.backend.posts;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

	Optional<Post> findById(Integer id);

	List<Post> findByAuthorId(Integer id);

}
