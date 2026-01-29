package com.revature.backend.users;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
public class UserController {

	private final UserService userService;

//	@PostMapping("/register")
//	@ResponseStatus(HttpStatus.CREATED)
//	public User postUser(@RequestBody NewUserDto user) {
//		return userService.createUser(user);
//	}

	@GetMapping("/api/users")
	@ResponseStatus(HttpStatus.OK)
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/api/users/{id}")
	@ResponseStatus(HttpStatus.OK)
	public User getUserById(@PathVariable Integer id) {
		return userService.getUserById(id);
	}
}
