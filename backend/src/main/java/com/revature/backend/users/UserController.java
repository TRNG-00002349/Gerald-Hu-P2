package com.revature.backend.users;

import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
public class UserController {

	private final UserService userService;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public User postUser(@Valid @RequestBody UserDto userDto) {
		return userService.createUser(userDto);
	}

	@GetMapping("/api/users")
	@ResponseStatus(HttpStatus.OK)
	public List<User> getAllUsers() {
		return userService.readAllUsers();
	}

	@GetMapping("/api/users/{id}")
	@ResponseStatus(HttpStatus.OK)
	public User getUserById(@PathVariable Integer id) {
		return userService.readUserById(id);
	}

	@PutMapping("/api/users/{id}")
	@ResponseStatus(HttpStatus.OK)
	public User putUserById(@PathVariable Integer id, @Valid @RequestBody UserDto user) {
		return userService.updateUserById(id, user);
	}
}
