package com.revature.backend.users;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	@Query("select u from User u where u.deleted = FALSE")
	List<User> findAll();

	Optional<User> findById(@PathVariable Integer id);
	// TODO: find users that AREN'T deleted

	// List<User> findByUsername(@Param("username") String name);
}