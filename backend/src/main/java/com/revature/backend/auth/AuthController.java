package com.revature.backend.auth;

import com.revature.backend.users.User;
import com.revature.backend.users.UserDto;
import com.revature.backend.users.UserService;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.Base64;

@RestController
public class AuthController {

	private final UserService userService;
	private final SecretKey secretKey;

	@Autowired
	public AuthController(UserService userService, @Value("${secretkey}") String secretKeyString) {
		this.userService = userService;
		byte[] decodedKey = Base64.getDecoder().decode(secretKeyString);
		this.secretKey = Keys.hmacShaKeyFor(decodedKey);
	}

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public User postUser(@Valid @RequestBody UserDto userDto) {
		return userService.createUser(userDto);
	}

}
