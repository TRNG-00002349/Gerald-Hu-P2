package com.revature.backend.users;

import jakarta.persistence.Id;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.PathVariable;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {


	User findById(@PathVariable Integer id);

	// List<User> findByUsername(@Param("username") String name);
}