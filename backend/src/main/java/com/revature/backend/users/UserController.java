package com.revature.backend.users;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
public class UserController {

	private final UserService userService;

	@GetMapping("/api/users")
	@ResponseStatus(HttpStatus.OK)
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
}
