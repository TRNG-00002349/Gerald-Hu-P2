package com.revature.backend.auth;

import com.revature.backend.users.User;
import com.revature.backend.users.UserDto;
import com.revature.backend.users.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

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
	public User registerUser(@Valid @RequestBody UserDto userDto) {
		return userService.createUser(userDto);
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String loginUser(@Valid @RequestBody AuthDto authDto, HttpServletResponse response) {
		User user = this.userService.login(authDto);

		String jwt = Jwts.builder()
				.subject("Authorization JWT")
				.claim("username", user.getUsername())
				.claim("role", "USER")
				.issuedAt(Date.from(Instant.now()))
				.expiration(Date.from(LocalDateTime.now().plusDays(3).atZone(ZoneId.systemDefault()).toInstant()))
				.signWith(secretKey)
				.compact();

		Cookie cookie = new Cookie("Authentication", jwt);
		cookie.setMaxAge(60*60*24*3);
		cookie.setHttpOnly(true);

		response.addCookie(cookie);
		return "{\"status\": 200, \"authenticated\": true}";
	}

}
