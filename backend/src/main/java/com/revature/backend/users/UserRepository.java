package com.revature.backend.users;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	List<User> findAllByDeletedFalse();

	Optional<User> findByIdAndDeletedFalse(Integer id);

	// List<User> findByUsername(@Param("username") String name);
}