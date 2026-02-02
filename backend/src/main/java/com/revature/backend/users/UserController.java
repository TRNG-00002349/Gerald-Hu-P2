package com.revature.backend.users;

import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public List<User> getAllUsers() {
		return userService.readAllUsers();
	}

	@GetMapping("/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public User getUserById(@PathVariable Integer userId) {
		return userService.readUserById(userId);
	}

	@PutMapping("/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public User putUserById(@PathVariable Integer userId, @Valid @RequestBody UserDto user) {
		return userService.updateUserById(userId, user);
	}

	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUserById(@PathVariable Integer userId) {
		userService.deleteUserById(userId);
	}
}
